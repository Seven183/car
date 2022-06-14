package cn.lvhaosir.service.impl;

import cn.lvhaosir.entity.MetaData;
import cn.lvhaosir.mapper.MataDataMapper;
import cn.lvhaosir.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MetaDataServiceImpl implements MetaDataService {

    @Autowired
    private MataDataMapper mataDataMapper;

    @Override
    public List<MetaData> queryMetaDataByType(String type) {
        Example example = new Example(MetaData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type",type);
        return mataDataMapper.selectByExample(example);
    }
}
