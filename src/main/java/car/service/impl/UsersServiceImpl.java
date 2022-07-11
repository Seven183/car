package car.service.impl;


import car.entity.Users;
import car.entity.UsersLoginLogs;
import car.mapper.UsersLoginLogMapper;
import car.mapper.UsersMapper;
import car.service.UsersService;
import car.utils.PageData;
import car.utils.PageParam;
import car.utils.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper baseMapper;
    @Autowired
    private UsersLoginLogMapper usersLoginLogMapper;

    @Override
    public PageData<Users> queryListByParam(Users users) {
        PageHelper.startPage(users.getPageNum(), users.getPageSize());
        List<Users> list = baseMapper.select(users);
        PageInfo<Users> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }

    @Override
    public PageData<Users> allUsers(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Users> list = baseMapper.selectAll();
        PageInfo<Users> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }

    @Override
    public Integer add(Users model) {
        model.setStatus(0);
        model.setIsDelete(0);
        return baseMapper.insert(model);
    }

    @Override
    public Integer delete(Integer userId) {
        Users users = new Users();
        users.setIsDelete(1);
        users.setUpdateTime(new Date());
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        return baseMapper.updateByExampleSelective(users, example);
    }


    @Override
    public Integer update(Users model) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", model.getUserId());
        return baseMapper.updateByExampleSelective(model, example);
    }

    public Users selectUserByUserId(Integer userId) {
        Users users = new Users();
        users.setUserId(userId);
        return baseMapper.selectOne(users);
    }

    @Override
    public Users login(Users model) {
        Jedis jedis = RedisUtils.getConn();
        String token = jedis.get("auth:token");
        if (token == null) {
            token = UUID.randomUUID().toString();
            jedis.set("auth:token", token, "NX","EX",3600);
        }

        Users users = baseMapper.selectOne(model);
        UsersLoginLogs usersLoginLogs = new UsersLoginLogs();
        usersLoginLogs.setUserName(model.getUserName());
        usersLoginLogs.setLoginTime(new Date());
        usersLoginLogs.setCreateTime(new Date());
//        usersLoginLogs.setExpirationTime(new Date(System.currentTimeMillis() + 120 * 60 * 1000));
        if (users != null) {
            usersLoginLogMapper.insert(usersLoginLogs);
        }
        return users;
    }

    @Override
    public Integer loginOut() {
        Jedis jedis = RedisUtils.getConn();
        jedis.del("auth:token");
        return 1;
//        Example example = new Example(UsersLoginLogs.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("userName", user.getUserName());
//        return usersLoginLogMapper.deleteByExample(example);
    }
}
