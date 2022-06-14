package cn.lvhaosir.controller;

import cn.lvhaosir.entity.MetaData;
import cn.lvhaosir.service.MetaDataService;
import cn.lvhaosir.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matadata")
public class MetaDataController {

    @Autowired
    MetaDataService metaDataService;

    @GetMapping(value = "/select/{type}")
    public Result<List<MetaData>> queryMetaDataByType(@PathVariable String type) {
        List<MetaData> metaData = metaDataService.queryMetaDataByType(type);
        return Result.ok(metaData);
    }
}
