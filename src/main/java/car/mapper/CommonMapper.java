package car.mapper;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;
import tk.mybatis.mapper.common.example.DeleteByExampleMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper;

public interface CommonMapper<T> extends BaseMapper<T>, MySqlMapper<T>, InsertSelectiveMapper<T>, UpdateByExampleSelectiveMapper<T>, SelectByExampleMapper<T>, DeleteByExampleMapper<T> {

}
