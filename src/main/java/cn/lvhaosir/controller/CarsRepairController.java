package cn.lvhaosir.controller;


import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.paramater.CarsRepairParameter;
import cn.lvhaosir.service.CarsRepairService;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.Result;
import cn.lvhaosir.utils.SystemException;
import cn.lvhaosir.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Set;

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

    /***
     * 根据carNumber分组
     * @return
     */
    @GetMapping(value = "/selectCarNumbers")
    public Result<Set<String>> selectCarNumbers() {
        Set<String> listCarNumbers = carsRepairService.selectCarNumbers();
        return Result.ok(listCarNumbers);
    }

    /***
     *  根据 carsRepairId 操作 status 值
     * @return
     */
    @GetMapping(value = "/statusOperate")
    public Result<Integer> statusOperate(String carsRepairNumber, Integer status) {
        Integer update = carsRepairService.statusOperate(carsRepairNumber, status);
        return Result.ok(update);
    }

    /***
     * 根据carNumber得到详细信息
     * @return
     */
    @GetMapping(value = "/detailsByCarsRepairNumber/{carsRepairNumber}")
    public Result<CarsRepairParameter> detailsByCarsRepairNumber(@PathVariable String carsRepairNumber) {
        CarsRepairParameter carsRepairDetails = carsRepairService.detailsByCarsRepairNumber(carsRepairNumber);
        return Result.ok(carsRepairDetails);
    }
}
