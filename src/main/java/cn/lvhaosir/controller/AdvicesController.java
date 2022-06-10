package cn.lvhaosir.controller;


import cn.lvhaosir.entity.Advices;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;
import cn.lvhaosir.service.AdvicesService;
import cn.lvhaosir.utils.Result;
import cn.lvhaosir.utils.SystemException;
import cn.lvhaosir.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/advices")
public class AdvicesController {

    @Autowired
    private AdvicesService advicesService;

    @PostMapping(value = "/addAdvice")
    public Result<String> addAdvice(@RequestBody Advices advice) {
        Integer add = advicesService.add(advice);
        return Result.ok(add == 1 ? SystemSuccess.ADD_DRIVER_SUCCESS.getMessage() : SystemException.ADD_DRIVER_FAILED.getMessage());
    }

    @GetMapping(value = "/deleteAdvice/{id}")
    public Result<String> deleteAdvice(@PathVariable Integer id) {
        Integer delete = advicesService.delete(id);
        return Result.ok(delete == 1 ? SystemSuccess.DELETE_DRIVER_SUCCESS.getMessage() : SystemException.DELETE_DRIVER_FAILED.getMessage());
    }

    @PostMapping(value = "/updateAdvice")
    public Result<String> updateAdvice(@RequestBody Advices advice) {
        Integer update = advicesService.update(advice);
        return Result.ok(update == 1 ? SystemSuccess.UPDATE_DRIVER_SUCCESS.getMessage() : SystemException.UPDATE_DRIVER_FAILED.getMessage());
    }

    @GetMapping(value = "/select/{id}")
    public Result<Advices> queryAll(@PathVariable Integer id) {
        Advices advices = advicesService.selectAdvicesById(id);
        return Result.ok(advices);
    }

    @GetMapping(value = "/queryLikeAdvices")
    public Result<PageData<Advices>> queryLikeAdvices(Advices advice) {
        PageData<Advices> advicesPageData = advicesService.queryLikeAdvices(advice);
        return Result.ok(advicesPageData);
    }

    @GetMapping(value = "/queryAllAdvice")
    public Result<PageData<Advices>> queryAllAdvice(PageParam pageParam) {
        PageData<Advices> advicesPageData = advicesService.queryAllAdvices(pageParam);
        return Result.ok(advicesPageData);
    }
}
