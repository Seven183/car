package car.controller;


import car.entity.Insurance;
import car.paramater.InsuranceParameter;
import car.service.InsuranceService;
import car.utils.PageData;
import car.utils.Result;
import car.utils.SystemException;
import car.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @PostMapping(value = "/addInsurance")
    public Result<String> addInsurance(@RequestBody InsuranceParameter insuranceParameter) {
        Integer add = insuranceService.add(insuranceParameter);
        return Result.ok(add == 1 ? SystemSuccess.ADD_INSURANCE_SUCCESS.getMessage() : SystemException.ADD_INSURANCE_FAILED.getMessage());
    }

    @GetMapping(value = "/deleteInsurance/{insuranceCode}")
    public Result<String> deleteInsurance(@PathVariable String insuranceCode) {
        Integer delete = insuranceService.delete(insuranceCode);
        return Result.ok(delete == 1 ? SystemSuccess.DELETE_INSURANCE_SUCCESS.getMessage() : SystemException.DELETE_INSURANCE_FAILED.getMessage());
    }

    @PostMapping(value = "/updateInsurance")
    public Result<String> updateInsurance(@RequestBody InsuranceParameter insuranceParameter) {
        Integer update = insuranceService.update(insuranceParameter);
        return Result.ok(update == 1 ? SystemSuccess.UPDATE_INSURANCE_SUCCESS.getMessage() : SystemException.UPDATE_INSURANCE_FAILED.getMessage());
    }

    @GetMapping(value = "/select/{insuranceCode}")
    public Result<InsuranceParameter> selectInsuranceByInsuranceCode(@PathVariable String insuranceCode) {
        InsuranceParameter insurance = insuranceService.selectInsuranceByInsuranceCode(insuranceCode);
        return Result.ok(insurance);
    }

    @GetMapping(value = "/allInsurance")
    public Result<PageData<Insurance>> allInsurance(InsuranceParameter insuranceParameter) throws ParseException {
        PageData<Insurance> insurance = insuranceService.allInsurance(insuranceParameter);
        return Result.ok(insurance);
    }
}
