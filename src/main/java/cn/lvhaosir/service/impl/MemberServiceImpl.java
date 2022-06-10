package cn.lvhaosir.service.impl;


import cn.lvhaosir.entity.Advices;
import cn.lvhaosir.entity.Member;
import cn.lvhaosir.mapper.MemberMapper;
import cn.lvhaosir.service.MemberService;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Integer add(Member member) {
        return memberMapper.insert(member);
    }

    @Override
    public Integer delete(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(Member member) {
        Example example = new Example(Advices.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberName",member.getMemberName());
        return memberMapper.updateByExampleSelective(member, example);
    }

    @Override
    public Member selectMemberById(Integer id) {
        Member member = new Member();
        member.setId(id);
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
    public PageData<Member> queryAllMembers(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        List<Member> list = memberMapper.selectAll();
        PageInfo<Member> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }
}
