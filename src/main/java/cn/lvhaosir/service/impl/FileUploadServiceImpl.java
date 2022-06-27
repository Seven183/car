package cn.lvhaosir.service.impl;

import cn.lvhaosir.result.PicUploadResult;
import cn.lvhaosir.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Value("${access-path}")
    private String access_path;

    @Value("${root-path}")
    private String root_path;


    @Override
    public PicUploadResult uplodadImg(MultipartFile multipartFile, HttpServletRequest request) {
        return uploadImgMethod(multipartFile, request);
    }


    @Override
    public PicUploadResult uploadManyImg(MultipartFile[] uploadFile, HttpServletRequest request) {
        List<String> imgPaths = new ArrayList<>();
        for (MultipartFile multipartFile : uploadFile) {
            if(!multipartFile.isEmpty()){
                PicUploadResult picUploadResult = uploadImgMethod(multipartFile, request);
                if(picUploadResult.isLegal()){
                    imgPaths.add(picUploadResult.getImgPath());
                }else{
                    return picUploadResult;
                }
            }
        }

        PicUploadResult picUploadResult = new PicUploadResult();
        picUploadResult.setLegal(true);
        picUploadResult.setImgPaths(imgPaths);
        return picUploadResult;
    }


    private PicUploadResult uploadImgMethod(MultipartFile multipartFile, HttpServletRequest request) {
        PicUploadResult picUploadResult = new PicUploadResult();

        if (multipartFile.isEmpty()) {
            //返回选择文件提示
            picUploadResult.setLegal(false);
            return picUploadResult;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/");
        String format = sdf.format(new Date());
        //存放上传文件的文件夹
        File file = new File(root_path + format);
        logger.info("-----------存放上传文件的文件夹路径 -- 【" + file.getAbsolutePath() + "】-----------");
        if (!file.isDirectory()) {
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字
        String oldName = multipartFile.getOriginalFilename();
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + oldName);
            multipartFile.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + access_path + oldName;
            logger.info("-----------【" + filePath + "】-----------");
            picUploadResult.setLegal(true);
            picUploadResult.setImgPath(filePath);
            return picUploadResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        picUploadResult.setLegal(false);
        return picUploadResult;
    }
}
