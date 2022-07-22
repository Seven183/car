package car.controller;


import car.entity.CarsRepair;
import car.paramater.CarsRepairParameter;
import car.service.CarsRepairService;
import car.utils.PageData;
import car.utils.Result;
import car.utils.SystemException;
import car.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/carsRepair")
public class CarsRepairController {

    @Autowired
    private CarsRepairService carsRepairService;

    @PostMapping(value = "/addCarsRepair")
    public Result<String> addCarsRepair(@RequestBody CarsRepairParameter carsRepair) {
        Integer add = carsRepairService.add(carsRepair);
        return Result.ok(add == 1 ? SystemSuccess.ADD_CAR_REPAIR_SUCCESS.getMessage() : SystemException.ADD_CAR_REPAIR_FAILED.getMessage());
    }

    @GetMapping(value = "/deleteCarsRepair/{carsRepairNumber}")
    public Result<String> deleteCarsRepair(@PathVariable String carsRepairNumber) {
        Integer delete = carsRepairService.delete(carsRepairNumber);
        return Result.ok(delete == 1 ? SystemSuccess.DELETE_CAR_REPAIR_SUCCESS.getMessage() : SystemException.DELETE_CAR_REPAIR_FAILED.getMessage());
    }

    @PostMapping(value = "/updateCarsRepair")
    public Result<String> updateCarsRepair(@RequestBody CarsRepairParameter carsRepair) {
        Integer update = carsRepairService.update(carsRepair);
        return Result.ok(update == 1 ? SystemSuccess.UPDATE_CAR_REPAIR_SUCCESS.getMessage() : SystemException.UPDATE_CAR_REPAIR_FAILED.getMessage());
    }

    @GetMapping(value = "/select/{carsRepairNumber}")
    public Result<CarsRepairParameter> selectCarsRepairByCarsRepairNumber(@PathVariable String carsRepairNumber) {
        CarsRepairParameter carsRepair = carsRepairService.selectCarsRepairByCarsRepairNumber(carsRepairNumber);
        return Result.ok(carsRepair);
    }

    @GetMapping(value = "/allCarsRepairs")
    public Result<PageData<CarsRepair>> queryAllCarsRepairs(CarsRepairParameter carsRepairParameter) throws ParseException {
        PageData<CarsRepair> queryParamList = carsRepairService.queryAllCarsRepairs(carsRepairParameter);
        return Result.ok(queryParamList);
    }

    @GetMapping(value = "/statusOperate")
    public Result<Integer> statusOperate(String carsRepairNumber, Integer status) {
        Integer update = carsRepairService.statusOperate(carsRepairNumber, status);
        return Result.ok(update);
    }

    @GetMapping(value = "/detailsByCarsRepairNumber/{carsRepairNumber}")
    public Result<CarsRepairParameter> detailsByCarsRepairNumber(@PathVariable String carsRepairNumber) {
        CarsRepairParameter carsRepairDetails = carsRepairService.detailsByCarsRepairNumber(carsRepairNumber);
        return Result.ok(carsRepairDetails);
    }

    @GetMapping(value = "/selectCarsRepairType")
    public Result<List<String>> selectCarsRepairType() {
        List<String> list = carsRepairService.selectCarsRepairType();
        return Result.ok(list);
    }

    @GetMapping(value = "/selectCarNumbers")
    public Result<List<String>> selectCarNumbers() {
        List<String> listCarNumbers = carsRepairService.selectCarNumbers();
        return Result.ok(listCarNumbers);
    }

    @GetMapping(value = "/selectPhone")
    public Result<List<String>> selectPhone() {
        List<String> listCarNumbers = carsRepairService.selectPhone();
        return Result.ok(listCarNumbers);
    }
}
