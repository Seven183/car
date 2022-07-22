package car.controller;


import car.entity.Drivers;
import car.paramater.DriverParameter;
import car.service.DriversService;
import car.utils.PageData;
import car.utils.Result;
import car.utils.SystemException;
import car.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriversController {

    @Autowired
    private DriversService driversService;


    @PostMapping(value = "/updateDriver")
    public Result<String> updateDriver(@RequestBody Drivers driver) {
        Integer update = driversService.update(driver);
        return Result.ok(update == 1 ? SystemSuccess.UPDATE_DRIVER_SUCCESS.getMessage() : SystemException.UPDATE_DRIVER_FAILED.getMessage());
    }

    @GetMapping(value = "/selectByCarsRepairNumber/{carsRepairNumber}")
    public Result<DriverParameter> selectDriverByCarsRepairNumber(@PathVariable String carsRepairNumber) {
        DriverParameter queryById = driversService.selectDriverByCarsRepairNumber(carsRepairNumber);
        return Result.ok(queryById);
    }

    @GetMapping(value = "/allDrivers")
    public Result<PageData<Drivers>> allDrivers(DriverParameter driverParameter) {
		PageData<Drivers> queryPageList = driversService.allDrivers(driverParameter);
        return Result.ok(queryPageList);
    }

    @GetMapping(value = "/detailsByCarsRepairNumber/{carsRepairNumber}")
    public Result<DriverParameter> detailsByCarsRepairNumber(@PathVariable String carsRepairNumber) {
        DriverParameter driverParameter = driversService.detailsByCarsRepairNumber(carsRepairNumber);
        return Result.ok(driverParameter);
    }


    @GetMapping(value = "/selectCarNumbers")
    public Result<List<String>> selectCarNumbers() {
        List<String> listCarNumbers = driversService.selectCarNumbers();
        return Result.ok(listCarNumbers);
    }

    @GetMapping(value = "/selectPhone")
    public Result<List<String>> selectPhone() {
        List<String> listCarNumbers = driversService.selectPhone();
        return Result.ok(listCarNumbers);
    }

    @GetMapping(value = "/selectCarBrand")
    public Result<List<String>> selectCarBrand() {
        List<String> listCarNumbers = driversService.selectCarBrand();
        return Result.ok(listCarNumbers);
    }

    @GetMapping(value = "/selectName")
    public Result<List<String>> selectName() {
        List<String> listCarNumbers = driversService.selectName();
        return Result.ok(listCarNumbers);
    }
}
