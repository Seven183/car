package cn.lvhaosir.service.impl;


import cn.lvhaosir.entity.Member;
import cn.lvhaosir.mapper.MemberMapper;
import cn.lvhaosir.paramater.MemberParameter;
import cn.lvhaosir.service.MemberService;
import cn.lvhaosir.utils.PageData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Integer add(Member member) {
        member.setCreateTime(new Date());
        member.setUpdateTime(new Date());
        return memberMapper.insert(member);
    }

    @Override
    public Integer delete(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
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
    public PageData<Member> queryLikeMembers(Member member) {
        PageHelper.startPage(member.getPageNum(),member.getPageSize());
        List<Member> list= memberMapper.select(member);
        PageInfo<Member> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }

    @Override
    public PageData<Member> allMember(MemberParameter memberParameter) {

        PageHelper.startPage(memberParameter.getPageNum(),memberParameter.getPageSize());
        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(memberParameter.getMemberName())) {
            criteria.andLike("memberName", "%" + memberParameter.getMemberName() + "%");
        }
        if (StringUtils.isNotBlank(memberParameter.getMemberSex())) {
            criteria.andLike("memberSex", "%" + memberParameter.getMemberSex() + "%");
        }
        if (StringUtils.isNotBlank(memberParameter.getPhone())) {
            criteria.andLike("phone", "%" + memberParameter.getPhone() + "%");
        }

        List<Member> list = memberMapper.selectByExample(example);
        list.sort(Comparator.comparing(Member::getUpdateTime).reversed());
        PageInfo<Member> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }
}
