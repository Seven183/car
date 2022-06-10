package cn.lvhaosir.service;

import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;
import cn.lvhaosir.entity.Users;

public interface UsersService {

    public Integer add(Users users);

    public Integer delete(Object id);

    public Integer update(Users model);

    public Users selectUserById(Integer id);

    public PageData<Users> queryListByParam(Users users);

    public PageData<Users> queryAllList(PageParam pageParam);

    public Users login(Users model);

    public Integer loginOut();

}
