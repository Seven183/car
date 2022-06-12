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

@RestController
@RequestMapping("/carsRepair")
public class CarsRepairController {

    @Autowired
    private CarsRepairService carsRepairService;

    @PostMapping(value = "/addCarsRepair")
    public Result<String> addCarsRepair(@RequestBody CarsRepair carsRepair) {
        Integer add = carsRepairService.add(carsRepair);
        return Result.ok(add == 1 ? SystemSuccess.ADD_CAR_REPAIR_SUCCESS.getMessage() : SystemException.ADD_CAR_REPAIR_FAILED.getMessage());
    }

    @GetMapping(value = "/deleteCarsRepair/{carsRepairId}")
    public Result<String> deleteCarsRepair(@PathVariable Integer carsRepairId) {
        Integer delete = carsRepairService.delete(carsRepairId);
        return Result.ok(delete == 1 ? SystemSuccess.DELETE_CAR_REPAIR_SUCCESS.getMessage() : SystemException.DELETE_CAR_REPAIR_FAILED.getMessage());
    }

    @PostMapping(value = "/updateCarsRepair")
    public Result<String> updateCarsRepair(@RequestBody CarsRepair carsRepair) {
        Integer update = carsRepairService.update(carsRepair);
        return Result.ok(update == 1 ? SystemSuccess.UPDATE_CAR_REPAIR_SUCCESS.getMessage() : SystemException.UPDATE_CAR_REPAIR_FAILED.getMessage());
    }

    @GetMapping(value = "/select/{carsRepairId}")
    public Result<CarsRepair> queryCarsRepairById(@PathVariable Integer carsRepairId) {
        CarsRepair carsRepair = carsRepairService.selectCarsRepairById(carsRepairId);
        return Result.ok(carsRepair);
    }

    @GetMapping(value = "/queryLike")
    public Result<PageData<CarsRepair>> queryLikeCarsRepair(CarsRepair carsRepair) {
        PageData<CarsRepair> carsRepairPageData = carsRepairService.queryLikeCarsRepair(carsRepair);
        return Result.ok(carsRepairPageData);
    }

    @GetMapping(value = "/allCarsRepair")
    public Result<PageData<CarsRepair>> queryAllCarsRepair(CarsRepairParameter carsRepairParameter) {
        PageData<CarsRepair> queryParamList = carsRepairService.queryAllCarsRepair(carsRepairParameter);
        return Result.ok(queryParamList);
    }
}
