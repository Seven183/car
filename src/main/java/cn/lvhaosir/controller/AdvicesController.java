package cn.lvhaosir.controller;


import cn.lvhaosir.entity.Advices;
import cn.lvhaosir.paramater.AdvicesParameter;
import cn.lvhaosir.service.AdvicesService;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/advice")
public class AdvicesController {

    @Autowired
    private AdvicesService advicesService;

    @GetMapping(value = "/allAdvices")
    public Result<PageData<Advices>> allAdvices(AdvicesParameter advicesParameter) {
        PageData<Advices> advicesPageData = advicesService.allAdvices(advicesParameter);
        return Result.ok(advicesPageData);
    }
}
