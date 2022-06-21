package cn.lvhaosir.service.impl;

import cn.lvhaosir.entity.CarsRepair;
import cn.lvhaosir.mapper.DashBoardMapper;
import cn.lvhaosir.result.CarBrandPerMonth;
import cn.lvhaosir.result.MoneyPerMonth;
import cn.lvhaosir.service.DashBoardService;
import cn.lvhaosir.utils.DateUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    DashBoardMapper dashBoardMapper;


    @Override
    public Double selectTotalAmount() {
        return dashBoardMapper.selectTotalAmount();
    }

    @Override
    public Double selectTotalAmountLastYear() {
        return dashBoardMapper.selectTotalAmountLastYear();
    }

    @Override
    public Integer selectCountUser() {
        return dashBoardMapper.selectCount(new CarsRepair());
    }

    @Override
    public Integer selectCountUserLastYear() {
        return dashBoardMapper.selectCountUserLastYear();
    }

    @Override
    public JSONObject selectAmountLastYearByMonth() {
        JSONObject jsonObject = new JSONObject();
        List<MoneyPerMonth> moneyPerMonths = dashBoardMapper.selectAmountLastYearByMonth();
        List<String> lastYearMonth = DateUtils.getLastYearMonth();
        for (int i = 1; i < lastYearMonth.size(); i++) {
            if (!moneyPerMonths.toString().contains(lastYearMonth.get(i))) {
                MoneyPerMonth moneyPerMonth = new MoneyPerMonth();
                moneyPerMonth.setMonth(lastYearMonth.get(i));
                moneyPerMonth.setMoney(0.0);
                moneyPerMonth.setUser(0);
                moneyPerMonths.add(moneyPerMonth);
            }
        }
        moneyPerMonths.sort(Comparator.comparing(MoneyPerMonth::getMonth));

        List<String> collectMonth = moneyPerMonths.stream().map(MoneyPerMonth::getMonth).collect(Collectors.toList());
        List<Double> collectMoney = moneyPerMonths.stream().map(MoneyPerMonth::getMoney).collect(Collectors.toList());
        List<Integer> collectUser = moneyPerMonths.stream().map(MoneyPerMonth::getUser).collect(Collectors.toList());
        jsonObject.put("collectMonthList", collectMonth);
        jsonObject.put("collectMoneyList", collectMoney);
        jsonObject.put("collectUserList", collectUser);
        return jsonObject;

    }

    @Override
    public List<CarBrandPerMonth> selectCarCountByBrandLastYear() {
        return dashBoardMapper.selectCarCountByBrandLastYear();
    }

    @Override
    public List<CarBrandPerMonth> selectCarCountByBrandAndNameLastMonth() {
        return dashBoardMapper.selectCarCountByBrandAndNameLastMonth();
    }

    @Override
    public List<CarBrandPerMonth> selectCarNameAndCountLastYear() {
        return dashBoardMapper.selectCarNameAndCountLastYear();
    }
}
