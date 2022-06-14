package cn.lvhaosir.service.impl;


import cn.lvhaosir.entity.Advices;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;
import cn.lvhaosir.mapper.AdvicesMapper;
import cn.lvhaosir.service.AdvicesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


@Service
public class AdvicesServiceImpl implements AdvicesService {

    @Autowired
    private AdvicesMapper advicesMapper;

    @Override
    public Integer add(Advices advice) {
        advice.setCreateTime(new Date());
        advice.setUpdateTime(new Date());
        return advicesMapper.insert(advice);
    }

    @Override
    public Integer delete(Integer id) {
        return advicesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(Advices advice) {
        advice.setUpdateTime(new Date());
        Example example = new Example(Advices.class);
        return advicesMapper.updateByExampleSelective(advice, example);
    }

    @Override
    public Advices selectAdvicesById(Integer id) {
        Advices advices = new Advices();
        advices.setAdvicesId(id);
        return advicesMapper.selectOne(advices);
    }

    @Override
    public PageData<Advices> queryLikeAdvices(Advices advice) {
        PageHelper.startPage(advice.getPageNum(),advice.getPageSize());
        List<Advices> list= advicesMapper.select(advice);
        PageInfo<Advices> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }

    @Override
    public PageData<Advices> allAdvices(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        List<Advices> list = advicesMapper.selectAll();
        PageInfo<Advices> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }
}
