package cn.lvhaosir.controller;


import cn.lvhaosir.entity.Drivers;
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

    @PostMapping(value = "/addDriver")
    public Result<String> addDriver(@RequestBody Drivers driver) {
        Integer add = driversService.add(driver);
        return Result.ok(add == 1 ? SystemSuccess.ADD_DRIVER_SUCCESS.getMessage() : SystemException.ADD_DRIVER_FAILED.getMessage());
    }

	@GetMapping(value = "/deleteDriver/{driverId}")
	public Result<String> deleteDriver(@PathVariable Integer driverId) {
		Integer delete = driversService.delete(driverId);
		return Result.ok(delete == 1 ? SystemSuccess.DELETE_DRIVER_SUCCESS.getMessage() : SystemException.DELETE_DRIVER_FAILED.getMessage());
	}

    @PostMapping(value = "/updateDriver")
    public Result<String> updateDriver(@RequestBody Drivers driver) {
        Integer update = driversService.update(driver);
        return Result.ok(update == 1 ? SystemSuccess.UPDATE_DRIVER_SUCCESS.getMessage() : SystemException.UPDATE_DRIVER_FAILED.getMessage());
    }

	@GetMapping(value = "/selectById/{driverId}")
	public Result<Drivers> selectDriverById(@PathVariable Integer driverId) {
		Drivers queryById = driversService.selectDriverById(driverId);
		return Result.ok(queryById);
	}

    @GetMapping(value = "/selectByCarsRepairNumber/{carsRepairNumber}")
    public Result<Drivers> selectDriverByCarsRepairNumber(@PathVariable String carsRepairNumber) {
        Drivers queryById = driversService.selectDriverByCarsRepairNumber(carsRepairNumber);
        return Result.ok(queryById);
    }

    @GetMapping(value = "/queryLikeDrivers")
    public Result<PageData<Drivers>> queryLikeDrivers(Drivers driver) {
        PageData<Drivers> pageDate = driversService.queryLikeDrivers(driver);
        return Result.ok(pageDate);
    }

    @GetMapping(value = "/allDrivers")
    public Result<PageData<Drivers>> allDrivers(DriverParameter driverParameter) {
		PageData<Drivers> queryPageList = driversService.allDrivers(driverParameter);
        return Result.ok(queryPageList);
    }


    private String uploadFile(MultipartFile file, HttpServletRequest request, String idCard) {
        //传入的是身份证号
        File targetFile = null;
        String substring = null;
        try {
            String path = request.getSession().getServletContext()
                    .getRealPath("upload");
            if (path == null) {
                path = "/yjdata/www/www/chebida/upload";
            }
            String fileName = file.getOriginalFilename();
            int lastIndexOf = fileName.lastIndexOf(".");
            substring = fileName.substring(lastIndexOf);
            targetFile = new File(path, idCard + substring);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            // request.setAttribute("filePath",targetFile.getAbsolutePath());
        } catch (Exception e) {
        }
        return idCard + substring;
    }
}
