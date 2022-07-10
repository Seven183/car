package cn.lvhaosir.controller;


import cn.lvhaosir.entity.Insurance;
import cn.lvhaosir.entity.SecondHandCar;
import cn.lvhaosir.paramater.InsuranceParameter;
import cn.lvhaosir.paramater.SecondHandCarParameter;
import cn.lvhaosir.service.InsuranceService;
import cn.lvhaosir.service.SecondHandCarService;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.Result;
import cn.lvhaosir.utils.SystemException;
import cn.lvhaosir.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/secondHandCar")
public class SecondHandCarController {

    @Autowired
    private SecondHandCarService secondHandCarService;

    @PostMapping(value = "/addSecondHandCar")
    public Result<String> addSecondHandCar(@RequestBody SecondHandCarParameter secondHandCarParameter) {
        Integer add = secondHandCarService.add(secondHandCarParameter);
        return Result.ok(add == 1 ? SystemSuccess.ADD_SECONDHAND_CAR_SUCCESS.getMessage() : SystemException.ADD_SECONDHAND_CAR_FAILED.getMessage());
    }

    @GetMapping(value = "/deleteSecondHandCar/{secondHandCarId}")
    public Result<String> deleteSecondHandCar(@PathVariable String secondHandCarId) {
        Integer delete = secondHandCarService.delete(secondHandCarId);
        return Result.ok(delete == 1 ? SystemSuccess.DELETE_SECONDHAND_CAR_SUCCESS.getMessage() : SystemException.DELETE_SECONDHAND_CAR_FAILED.getMessage());
    }

    @PostMapping(value = "/updateSecondHandCar")
    public Result<String> updateSecondHandCar(@RequestBody SecondHandCarParameter secondHandCarParameter) {
        Integer update = secondHandCarService.update(secondHandCarParameter);
        return Result.ok(update == 1 ? SystemSuccess.UPDATE_SECONDHAND_CAR_SUCCESS.getMessage() : SystemException.UPDATE_SECONDHAND_CAR_FAILED.getMessage());
    }

    @GetMapping(value = "/select/{secondHandCarId}")
    public Result<SecondHandCarParameter> selectSecondHandCarById(@PathVariable Integer secondHandCarId) {
        SecondHandCarParameter secondHandCarParameter = secondHandCarService.selectSecondHandCarById(secondHandCarId);
        return Result.ok(secondHandCarParameter);
    }

    @GetMapping(value = "/allSecondHandCar")
    public Result<PageData<SecondHandCar>> allSecondHandCar(SecondHandCarParameter secondHandCarParameter) throws ParseException {
        PageData<SecondHandCar> secondHandCar = secondHandCarService.allSecondHandCar(secondHandCarParameter);
        return Result.ok(secondHandCar);
    }
}
