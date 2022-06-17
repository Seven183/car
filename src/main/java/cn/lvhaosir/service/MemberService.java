package cn.lvhaosir.service;

import cn.lvhaosir.entity.Member;
import cn.lvhaosir.paramater.MemberParameter;
import cn.lvhaosir.utils.PageData;

import java.util.Set;

public interface MemberService {

    public Integer add(Member member);

    public Integer delete(Integer memberId);

    public Integer update(Member member);

    public Member selectMemberById(Integer memberId);

    public PageData<Member> allMember(MemberParameter memberParameter);

    public Set<String> selectCarNumbers();
}
