package car.service;


import car.result.CarBrandPerMonth;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface DashBoardService {

    public Double selectTotalAmount();

    public Double selectTotalAmountLastYear();

    public Integer selectCountUser();

    public Integer selectCountUserLastYear();

    public JSONObject selectAmountLastYearByMonth();

    public JSONObject selectUserCountLastYearByMonth();

    public List<CarBrandPerMonth> selectCarCountByBrandLastYear();

    public List<CarBrandPerMonth> selectCarCountByBrandAndNameLastMonth();

    public List<CarBrandPerMonth> selectCarNameAndCountLastYear();

}
