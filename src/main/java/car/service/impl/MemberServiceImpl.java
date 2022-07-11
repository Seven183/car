package car.service.impl;


import car.entity.Member;
import car.mapper.MemberMapper;
import car.paramater.MemberParameter;
import car.service.MemberService;
import car.utils.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Integer add(Member member) {
        member.setCreateTime(new Date());
        member.setUpdateTime(new Date());
        member.setStatus(0);
        member.setIsDelete(0);
        return memberMapper.insert(member);
    }

    @Override
    public Integer delete(Integer id) {
        Member member = new Member();
        member.setIsDelete(1);
        member.setUpdateTime(new Date());
        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId", id);
        return memberMapper.updateByExampleSelective(member, example);
//        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(Member member) {
        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId",member.getMemberId());
        return memberMapper.updateByExampleSelective(member, example);
    }

    @Override
    public Member selectMemberById(Integer memberId) {
        Member member = new Member();
        member.setMemberId(memberId);
        return memberMapper.selectOne(member);
    }

    @Override
    public Set<String> selectCarNumbers() {
        List<String> list = memberMapper.selectCarNumbers();
        return new HashSet<String>(list);
    }

    @Override
    public PageData<Member> allMember(MemberParameter memberParameter) {

        PageHelper.startPage(memberParameter.getPageNum(),memberParameter.getPageSize());
        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();

        if (memberParameter.getIsDelete() != null) {
            criteria.andEqualTo("isDelete", memberParameter.getIsDelete());
        } else {
            criteria.andEqualTo("isDelete", 0);
        }
        if (StringUtils.isNotBlank(memberParameter.getCarNumber())) {
            criteria.andLike("carNumber", "%" + memberParameter.getCarNumber() + "%");
        }
        if (StringUtils.isNotBlank(memberParameter.getMemberName())) {
            criteria.andLike("memberName", "%" + memberParameter.getMemberName() + "%");
        }
        if (StringUtils.isNotBlank(memberParameter.getMemberSex())) {
            criteria.andLike("memberSex", "%" + memberParameter.getMemberSex() + "%");
        }
        if (StringUtils.isNotBlank(memberParameter.getPhone())) {
            criteria.andLike("phone", "%" + memberParameter.getPhone() + "%");
        }
        if (StringUtils.isNotBlank(memberParameter.getAddress())) {
            criteria.andLike("address", "%" + memberParameter.getAddress() + "%");
        }
        if (StringUtils.isNotBlank(memberParameter.getCarBrand())) {
            criteria.andLike("carBrand", "%" + memberParameter.getCarBrand()+ "%");
        }
        List<Member> list = memberMapper.selectByExample(example);
        list.sort(Comparator.comparing(Member::getUpdateTime).reversed());
        PageInfo<Member> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }
}
