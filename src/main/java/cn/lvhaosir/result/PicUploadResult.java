package cn.lvhaosir.result;


import lombok.Data;

import java.util.List;

@Data
public class PicUploadResult {

    private boolean isLegal;

    private String imgPath;

    private List<String> imgPaths;
}
