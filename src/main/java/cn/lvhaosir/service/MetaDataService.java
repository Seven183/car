package cn.lvhaosir.service;


import cn.lvhaosir.entity.MetaData;

import java.util.List;

public interface MetaDataService {

    public List<MetaData> queryMetaDataByType(String type);
}
