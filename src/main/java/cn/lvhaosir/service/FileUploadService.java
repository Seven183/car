package cn.lvhaosir.service;

import cn.lvhaosir.result.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {

    //单图片上传
    PicUploadResult uplodadImg(MultipartFile uploadFile, HttpServletRequest request);

    //多图片上传
    PicUploadResult uploadManyImg(MultipartFile[] uploadFile, HttpServletRequest request);
}
