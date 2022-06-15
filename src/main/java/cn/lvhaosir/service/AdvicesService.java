package cn.lvhaosir.service;


import cn.lvhaosir.entity.Advices;
import cn.lvhaosir.paramater.AdvicesParameter;
import cn.lvhaosir.utils.PageData;

public interface AdvicesService {

    public Integer add(Advices advice);

    public Integer delete(Integer adviceId);

    public Integer update(Advices advice);

    public Advices selectAdvicesById(Integer adviceId);

    public PageData<Advices> queryLikeAdvices(Advices advice);

    public PageData<Advices> allAdvices(AdvicesParameter advicesParameter);
}
