package cn.lvhaosir.controller;

import cn.lvhaosir.service.DashBoardService;
import cn.lvhaosir.utils.Result;
import cn.lvhaosir.utils.SystemException;
import cn.lvhaosir.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashBoard")
public class DashBoardController {

    @Autowired
    private DashBoardService dashBoardService;

    @GetMapping(value = "/selectTotalAmount")
    public Result<Double> selectTotalAmount() {
        Double aDouble = dashBoardService.selectTotalAmount();
        return Result.ok(aDouble);
    }

    @GetMapping(value = "/selectAmountLastYearByMonth")
    public Result<String> selectAmountLastYearByMonth() {
        Integer add = dashBoardService.selectAmountLastYearByMonth();
        return Result.ok(add == 1 ? SystemSuccess.ADD_DRIVER_SUCCESS.getMessage() : SystemException.ADD_DRIVER_FAILED.getMessage());
    }

    @GetMapping(value = "/selectCountUser")
    public Result<String> selectCountUser() {
        Integer add = dashBoardService.selectCountUser();
        return Result.ok(add == 1 ? SystemSuccess.ADD_DRIVER_SUCCESS.getMessage() : SystemException.ADD_DRIVER_FAILED.getMessage());
    }

    @GetMapping(value = "/selectCountUserLastYear")
    public Result<String> selectCountUserLastYear() {
        Integer add = dashBoardService.selectCountUserLastYear();
        return Result.ok(add == 1 ? SystemSuccess.ADD_DRIVER_SUCCESS.getMessage() : SystemException.ADD_DRIVER_FAILED.getMessage());
    }

    @GetMapping(value = "/selectCarCountByBrandLastYear")
    public Result<String> selectCarCountByBrandLastYear() {
        Integer add = dashBoardService.selectCarCountByBrandLastYear();
        return Result.ok(add == 1 ? SystemSuccess.ADD_DRIVER_SUCCESS.getMessage() : SystemException.ADD_DRIVER_FAILED.getMessage());
    }
}
