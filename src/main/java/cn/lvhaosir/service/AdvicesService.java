package cn.lvhaosir.service;


import cn.lvhaosir.entity.Advices;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;

public interface AdvicesService {

    public Integer add(Advices advice);

    public Integer delete(Integer id);

    public Integer update(Advices advice);

    public Advices selectAdvicesById(Integer id);

    public PageData<Advices> queryLikeAdvices(Advices advice);

    public PageData<Advices> queryAllAdvices(PageParam pageParam);
}
