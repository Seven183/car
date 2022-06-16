package cn.lvhaosir.controller;

import cn.lvhaosir.entity.Cars;
import cn.lvhaosir.paramater.CarParameter;
import cn.lvhaosir.service.CarsService;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.Result;
import cn.lvhaosir.utils.SystemException;
import cn.lvhaosir.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/car")
public class CarsController {

    @Autowired
    private CarsService carsService;

    @PostMapping(value = "/addCar")
    public Result<String> addCar(@RequestBody Cars car) {
        Integer add = carsService.add(car);
        return Result.ok(add == 1 ? SystemSuccess.ADD_CAR_SUCCESS.getMessage() : SystemException.ADD_CAR_FAILED.getMessage());
    }

    @GetMapping(value = "/deleteCar/{carId}")
    public Result<String> deleteCar(@PathVariable Integer carId) {
        Integer delete = carsService.delete(carId);
        return Result.ok(delete == 1 ? SystemSuccess.DELETE_CAR_SUCCESS.getMessage() : SystemException.DELETE_CAR_FAILED.getMessage());
    }

    @PostMapping(value = "/updateCar")
    public Result<String> updateCar(@RequestBody Cars car) {
        Integer update = carsService.update(car);
        return Result.ok(update == 1 ? SystemSuccess.UPDATE_CAR_SUCCESS.getMessage() : SystemException.UPDATE_CAR_FAILED.getMessage());
    }

    @GetMapping(value = "/select/{carId}")
    public Result<Cars> selectCarById(@PathVariable Integer carId) {
        Cars car = carsService.selectCarById(carId);
        return Result.ok(car);
    }

    @GetMapping(value = "/queryLikeCars")
    public Result<PageData<Cars>> queryLikeCars(Cars car) {
        PageData<Cars> carsPageData = carsService.queryLikeCars(car);
        return Result.ok(carsPageData);
    }

    @GetMapping(value = "/allCars")
    public Result<PageData<Cars>> allCars(CarParameter carParameter) {
        PageData<Cars> carsPageData = carsService.allCars(carParameter);
        return Result.ok(carsPageData);
    }
}
