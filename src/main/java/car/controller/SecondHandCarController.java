package car.controller;


import car.entity.SecondHandCar;
import car.paramater.SecondHandCarParameter;
import car.service.SecondHandCarService;
import car.utils.PageData;
import car.utils.Result;
import car.utils.SystemException;
import car.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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

    /**
     * 查询所有记录车牌号
     * @return
     */
    @GetMapping(value = "/selectCarNumbers")
    public Result<List<String>> selectCarNumbers(){
        List<String> secondHandCar = secondHandCarService.selectCarNumbers();
        return Result.ok(secondHandCar);
    }

    /**
     * 查询所有记录车牌号
     * @return
     */
    @GetMapping(value = "/selectCarBrands")
    public Result<List<String>> selectCarBrands(){
        List<String> secondHandCar = secondHandCarService.selectCarBrands();
        return Result.ok(secondHandCar);
    }

    /**
     * 查询所有记录买家手机号
     * @return
     */
    @GetMapping(value = "/selectBuyerPhones")
    public Result<List<String>> selectBuyerPhones(){
        List<String> secondHandCar = secondHandCarService.selectBuyerPhones();
        return Result.ok(secondHandCar);
    }

    /**
     * 查询所有记录买家姓名
     * @return
     */
    @GetMapping(value = "/selectBuyerUsers")
    public Result<List<String>> selectBuyerUsers(){
        List<String> secondHandCar = secondHandCarService.selectBuyerUsers();
        return Result.ok(secondHandCar);
    }

    /**
     * 查询所有记录买家身份证号
     * @return
     */
    @GetMapping(value = "/selectBuyerIdCards")
    public Result<List<String>> selectBuyerIdCards(){
        List<String> secondHandCar = secondHandCarService.selectBuyerIdCards();
        return Result.ok(secondHandCar);
    }
}
