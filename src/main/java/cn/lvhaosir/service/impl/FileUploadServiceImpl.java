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
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Value("${file.save-path}")
    private String baseFilePath;
    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    private final static String UPLOAD_PATH_PREFIX = "statics/uploadFile/";


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
        picUploadResult.setImgPahts(imgPaths);
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
        File file = new File(baseFilePath + format);
        logger.info("-----------存放上传文件的文件夹【" + file + "】-----------");
        logger.info("-----------输出文件夹绝对路径 -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径【" + file.getAbsolutePath() + "】-----------");
        if (!file.isDirectory()) {
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = multipartFile.getOriginalFilename();
        logger.info("-----------文件原始的名字【" + oldName + "】-----------");
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        logger.info("-----------文件要保存后的新名字【" + newName + "】-----------");
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            multipartFile.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/picbook/" + UPLOAD_PATH_PREFIX + format + newName;
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
