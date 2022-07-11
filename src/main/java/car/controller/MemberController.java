package car.controller;


import car.entity.Member;
import car.paramater.MemberParameter;
import car.service.MemberService;
import car.utils.PageData;
import car.utils.Result;
import car.utils.SystemException;
import car.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


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

    @GetMapping(value = "/deleteMember/{memberId}")
    public Result<String> deleteMember(@PathVariable Integer memberId) {
        Integer delete = memberService.delete(memberId);
        return Result.ok(delete == 1 ? SystemSuccess.DELETE_MEMBER_SUCCESS.getMessage() : SystemException.DELETE_MEMBER_FAILED.getMessage());
    }

    @PostMapping(value = "/updateMember")
    public Result<String> updateMember(@RequestBody Member member) {
        Integer update = memberService.update(member);
        return Result.ok(update == 1 ? SystemSuccess.UPDATE_MEMBER_SUCCESS.getMessage() : SystemException.UPDATE_MEMBER_FAILED.getMessage());
    }

    @GetMapping(value = "/select/{memberId}")
    public Result<Member> selectMemberById(@PathVariable Integer memberId) {
        Member member = memberService.selectMemberById(memberId);
        return Result.ok(member);
    }

    @GetMapping(value = "/allMember")
    public Result<PageData<Member>> allMember(MemberParameter memberParameter) {
        PageData<Member> memberPageData = memberService.allMember(memberParameter);
        return Result.ok(memberPageData);
    }

    @GetMapping(value = "/selectCarNumbers")
    public Result<Set<String>> selectCarNumbers() {
        Set<String> advicesPageData = memberService.selectCarNumbers();
        return Result.ok(advicesPageData);
    }
}
