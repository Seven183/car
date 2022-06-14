package cn.lvhaosir.controller;


import cn.lvhaosir.entity.Member;
import cn.lvhaosir.service.MemberService;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;
import cn.lvhaosir.utils.Result;
import cn.lvhaosir.utils.SystemException;
import cn.lvhaosir.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/addMember")
    public Result<String> addMember(@RequestBody Member member) {
        Integer add = memberService.add(member);
        return Result.ok(add == 1 ? SystemSuccess.ADD_MEMBER_SUCCESS.getMessage() : SystemException.ADD_MEMBER_FAILED.getMessage());
    }

    @GetMapping(value = "/deleteMember/{id}")
    public Result<String> deleteMember(@PathVariable Integer id) {
        Integer delete = memberService.delete(id);
        return Result.ok(delete == 1 ? SystemSuccess.DELETE_MEMBER_SUCCESS.getMessage() : SystemException.DELETE_MEMBER_FAILED.getMessage());
    }

    @PostMapping(value = "/updateMember")
    public Result<String> updateMember(@RequestBody Member member) {
        Integer update = memberService.update(member);
        return Result.ok(update == 1 ? SystemSuccess.UPDATE_MEMBER_SUCCESS.getMessage() : SystemException.UPDATE_MEMBER_FAILED.getMessage());
    }

    @GetMapping(value = "/select/{id}")
    public Result<Member> selectMemberById(@PathVariable Integer id) {
        Member member = memberService.selectMemberById(id);
        return Result.ok(member);
    }

    @GetMapping(value = "/queryLikeMember")
    public Result<PageData<Member>> queryLikeAdvices(Member member) {
        PageData<Member> advicesPageData = memberService.queryLikeMembers(member);
        return Result.ok(advicesPageData);
    }

    @GetMapping(value = "/allMember")
    public Result<PageData<Member>> allMember(PageParam pageParam) {
        PageData<Member> memberPageData = memberService.allMember(pageParam);
        return Result.ok(memberPageData);
    }
}
