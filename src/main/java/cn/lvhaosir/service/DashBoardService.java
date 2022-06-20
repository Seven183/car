package cn.lvhaosir.service;


public interface DashBoardService {

    public Double selectTotalAmount();

    public Integer selectAmountLastYearByMonth();

    public Integer selectCountUser();

    public Integer selectCountUserLastYear();

    public Integer selectCarCountByBrandLastYear();

}
