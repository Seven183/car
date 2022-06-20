package cn.lvhaosir.service.impl;

import cn.lvhaosir.mapper.DashBoardMapper;
import cn.lvhaosir.service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    DashBoardMapper dashBoardMapper;


    @Override
    public Double selectTotalAmount() {
        return dashBoardMapper.selectTotalAmount();
    }

    @Override
    public Integer selectAmountLastYearByMonth() {
        return null;
    }

    @Override
    public Integer selectCountUser() {
        return null;
    }

    @Override
    public Integer selectCountUserLastYear() {
        return null;
    }

    @Override
    public Integer selectCarCountByBrandLastYear() {
        return null;
    }
}
