package cn.lvhaosir.controller;


import cn.lvhaosir.entity.Drivers;
import cn.lvhaosir.paramater.CarsRepairParameter;
import cn.lvhaosir.paramater.DriverParameter;
import cn.lvhaosir.service.DriversService;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.Result;
import cn.lvhaosir.utils.SystemException;
import cn.lvhaosir.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

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
}
