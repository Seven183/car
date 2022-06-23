package cn.lvhaosir.service;


import cn.lvhaosir.entity.Advices;
import cn.lvhaosir.paramater.AdvicesParameter;
import cn.lvhaosir.utils.PageData;

public interface AdvicesService {

    public Advices selectAdvicesByCarsRepairNumber(String carsRepairNumber);

    public PageData<Advices> allAdvices(AdvicesParameter advicesParameter);
}
