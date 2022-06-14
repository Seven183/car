package cn.lvhaosir.service;

import cn.lvhaosir.entity.Member;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;

public interface MemberService {

    public Integer add(Member member);

    public Integer delete(Integer id);

    public Integer update(Member member);

    public Member selectMemberById(Integer id);

    public PageData<Member> queryLikeMembers(Member member);

    public PageData<Member> allMember(PageParam pageParam);
}
