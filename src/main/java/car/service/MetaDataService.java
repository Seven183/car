package car.service;


import car.entity.MetaData;

import java.util.List;

public interface MetaDataService {

    public List<MetaData> selectMetaDataByType(String type);
}
