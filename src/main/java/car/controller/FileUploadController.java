package car.controller;

import car.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


@RestController
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    public static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".png"};

    @PostMapping(value = "/fileUpload")
    public Result fileUpload(@RequestParam(value = "file") MultipartFile[] files, HttpServletRequest request) throws IOException {

        boolean isFlag = false;

        if (files.length == 0) {
            return Result.failed("上传文件为空");
        }

        for (MultipartFile uploadFile : files) {
            for (String type : IMAGE_TYPE) {
                if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
                    isFlag = true;
                    break;
                }
            }
        }
        if (isFlag) {
            String filePath = "";
            for (MultipartFile multipartFile : files) {
                filePath = uploadImgMethod(multipartFile, request);
                filePath = filePath + ",";
            }
            return Result.ok(filePath.substring(0, filePath.length()-1));
        } else {
            return Result.failed("上传格式不对");
        }
    }

    @GetMapping(value = "/fileDelete")
    public Result<Boolean> fileDelete(String imageName) {
        File file;
        boolean win = System.getProperty("os.name").toLowerCase().contains("win");
        if (!win) {
            file = new File("/usr/local/software/car/uploadFile/" + imageName);
            logger.info("<---------删除了linux图片------->" + file.getAbsolutePath());
            return Result.ok(file.delete());
        } else {
            file = new File("D://uploadFile//" + File.separator + imageName);
            logger.info("<---------删除了windows图片----->" + file.getAbsolutePath());
            return Result.ok(file.delete());
        }
    }


    private String uploadImgMethod(MultipartFile multipartFile, HttpServletRequest request) {

        File file;
        //存放上传文件的文件夹
        boolean win = System.getProperty("os.name").toLowerCase().contains("win");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd/");
//        String date = simpleDateFormat.format(new Date());
//        if (!win) {
//            file = new File("file:/usr/local/softWare/car/uploadFile/" + date);
//            logger.info("-----------存放上传文件的文件夹路径 -- 【" + file + "】-----------");
//        } else {
//            file = new File("D://uploadFile//" + date);
//            logger.info("-----------存放上传文件的文件夹路径 -- 【" + file + "】-----------");
//        }
        if (!win) {
            file = new File("/usr/local/software/car/uploadFile/");
            logger.info("-----------存放上传文件的文件夹路径 -- 【" + file + "】-----------");
        } else {
            file = new File("D://uploadFile//");
            logger.info("-----------存放上传文件的文件夹路径 -- 【" + file + "】-----------");
        }
        if (!file.isDirectory()) {
            file.mkdirs();
        }

        String fileName = multipartFile.getOriginalFilename();
//        String extension = fileName.substring(fileName.indexOf("."));
//        fileName = UUID.randomUUID() + extension;

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
