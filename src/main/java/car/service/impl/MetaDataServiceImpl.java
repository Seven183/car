package car.service.impl;

import car.entity.MetaData;
import car.mapper.MataDataMapper;
import car.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MetaDataServiceImpl implements MetaDataService {

    @Autowired
    private MataDataMapper mataDataMapper;

    @Override
    public List<MetaData> selectMetaDataByType(String type) {
        Example example = new Example(MetaData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type",type);
        return mataDataMapper.selectByExample(example);
    }
}
