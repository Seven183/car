package car.controller;


import car.entity.Advices;
import car.paramater.AdvicesParameter;
import car.service.AdvicesService;
import car.utils.PageData;
import car.utils.Result;
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
