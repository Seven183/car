package car.service;

import car.entity.Users;
import car.utils.PageData;
import car.utils.PageParam;

public interface UsersService {

    public Integer add(Users users);

    public Integer delete(Integer userId);

    public Integer update(Users model);

    public Users selectUserByUserId(Integer userId);

    public PageData<Users> queryListByParam(Users users);

    public PageData<Users> allUsers(PageParam pageParam);

    public Users login(Users model);

    public Integer loginOut();

}
