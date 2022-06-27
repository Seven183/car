package cn.lvhaosir.controller;

import cn.lvhaosir.result.PicUploadResult;
import cn.lvhaosir.service.FileUploadService;
import cn.lvhaosir.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

    // 允许上传的格式 图片形式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".png", ".jfif"};

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/uploadImg")
    public Result<Object> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        boolean isFlag = false;
        for (String type : IMAGE_TYPE) {
            System.out.println(file.getOriginalFilename());
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                isFlag = true;
                break;
            }
        }
        if (isFlag) {
            PicUploadResult picUploadResult = fileUploadService.uplodadImg(file, request);
            boolean isLegal = picUploadResult.isLegal();

            if (isLegal) {
                Map resMap = new HashMap<>();
                resMap.put("imgPath", picUploadResult.getImgPath());
                return Result.ok(resMap);
            } else {
                return Result.error("图片上传有误");
            }
        } else {
            return Result.error("上传的图片格式必须为:bmp,jpg,jpeg,png");
        }
    }

    @PostMapping("/uploadManyImg")
    public Result<Object> uploadManyImg(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        boolean isFlag = false;
        for (MultipartFile uploadFile : files) {
            for (String type : IMAGE_TYPE) {
                if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
                    isFlag = true;
                    break;
                }
            }
        }
        if (isFlag) {
            PicUploadResult picUploadResult = fileUploadService.uploadManyImg(files, request);
            boolean isLegal = picUploadResult.isLegal();
            if (isLegal) {
                Map resMap = new HashMap<>();
                resMap.put("imgPaths", picUploadResult.getImgPaths());
                return Result.ok(resMap);
            } else {
                return Result.error("图片上传有误");
            }
        } else {
            return Result.error("上传的图片格式必须为:bmp,jpg,jpeg,png");
        }
    }
}
