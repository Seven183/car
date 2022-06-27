package cn.lvhaosir.controller;

import cn.lvhaosir.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    public static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".png"};

    @PostMapping(value = "/fileUpload")
    public Result fileUpload(@RequestParam(value = "files") MultipartFile[] files , HttpServletRequest request) throws IOException {

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
            List<String> imgPaths = new ArrayList<>();
            for (MultipartFile multipartFile : files) {
                String filePath = uploadImgMethod(multipartFile, request);
                imgPaths.add(filePath);
            }
            return Result.ok(imgPaths);
        } else {
            return Result.failed(new ArrayList(Collections.singleton("上传格式不对")));
        }

//        ArrayList<String> list = new ArrayList<>();
//        // 允许上传的格式 图片形式
//        for (MultipartFile file : files) {
//            String fileName = file.getOriginalFilename();  // 文件名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
//            fileName = UUID.randomUUID().toString().replace("-", "") + suffixName; // 新文件名
//
//            String filePath = "D://fileUpload//"; // 上传后的路径
//
//            File dest = new File(filePath + fileName);
//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();
//            }
//            file.transferTo(dest);
//            String filename = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +  "/fileUpload/" + fileName;
//            list.add(filename);
//        }
//        return Result.ok(list);
    }


    private String uploadImgMethod(MultipartFile multipartFile, HttpServletRequest request) {

        File file;
        //存放上传文件的文件夹
        boolean win = System.getProperty("os.name").toLowerCase().contains("win");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd/");
        String date = simpleDateFormat.format(new Date());
        if (!win) {
            file = new File("file:/usr/local/softWare/car/uploadFile/" + date);
            logger.info("-----------存放上传文件的文件夹路径 -- 【" + file + "】-----------");
        } else {
            file = new File("D://uploadFile//" + date);
            logger.info("-----------存放上传文件的文件夹路径 -- 【" + file + "】-----------");
        }
        if (!file.isDirectory()) { file.mkdirs(); }

        String fileName = multipartFile.getOriginalFilename();
        String extension = fileName.substring(fileName.indexOf("."));
        fileName = UUID.randomUUID() + extension;

        try {
            //构建真实的文件路径
            File newFile = new File(file + File.separator + fileName);
            multipartFile.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + fileName;
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
