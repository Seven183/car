package car.controller;

import car.result.CarBrandPerMonth;
import car.service.DashBoardService;
import car.utils.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value = "/selectTotalAmountLastYear")
    public Result<Double> selectTotalAmountLastYear() {
        Double aDouble = dashBoardService.selectTotalAmountLastYear();
        return Result.ok(aDouble);
    }

    @GetMapping(value = "/selectCountUser")
    public Result<Integer> selectCountUser() {
        Integer integer = dashBoardService.selectCountUser();
        return Result.ok(integer);
    }

    @GetMapping(value = "/selectCountUserLastYear")
    public Result<Integer> selectCountUserLastYear() {
        Integer integer = dashBoardService.selectCountUserLastYear();
        return Result.ok(integer);
    }

    //近一年每月收入(元)
    @GetMapping(value = "/selectAmountLastYearByMonth")
    public Result<JSONObject> selectAmountLastYearByMonth() {
        JSONObject list = dashBoardService.selectAmountLastYearByMonth();
        return Result.ok(list);
    }

    //近一年每月用户数
    @GetMapping(value = "/selectUserCountLastYearByMonth")
    public Result<JSONObject> selectUserCountLastYearByMonth() {
        JSONObject list = dashBoardService.selectUserCountLastYearByMonth();
        return Result.ok(list);
    }

    //近一年维修车品牌和数量
    @GetMapping(value = "/selectCarCountByBrandLastYear")
    public Result<List<CarBrandPerMonth>> selectCarCountByBrandLastYear() {
        List<CarBrandPerMonth> list = dashBoardService.selectCarCountByBrandLastYear();
        return Result.ok(list);
    }

    //近一个月维修车名和数量
    @GetMapping(value = "/selectCarCountByBrandAndNameLastMonth")
    public Result<List<CarBrandPerMonth>> selectCarCountByBrandAndNameLastMonth() {
        List<CarBrandPerMonth> list = dashBoardService.selectCarCountByBrandAndNameLastMonth();
        return Result.ok(list);
    }

    //近一个年维修车名和数量
    @GetMapping(value = "/selectCarNameAndCountLastYear")
    public Result<List<CarBrandPerMonth>> selectCarNameAndCountLastYear() {
        List<CarBrandPerMonth> list = dashBoardService.selectCarNameAndCountLastYear();
        return Result.ok(list);
    }
}
