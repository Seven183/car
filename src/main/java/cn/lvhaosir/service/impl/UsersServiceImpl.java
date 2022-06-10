package cn.lvhaosir.service.impl;


import cn.lvhaosir.entity.Users;
import cn.lvhaosir.entity.UsersLoginLogs;
import cn.lvhaosir.mapper.UsersLoginLogMapper;
import cn.lvhaosir.mapper.UsersMapper;
import cn.lvhaosir.service.UsersService;
import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;
import cn.lvhaosir.utils.RedisUtils;
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
    public PageData<Users> queryAllList(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Users> list = baseMapper.selectAll();
        PageInfo<Users> pageInfo = PageInfo.of(list);
        return new PageData<>(list, pageInfo.getTotal());
    }

    @Override
    public Integer add(Users model) {
        return baseMapper.insert(model);
    }

    @Override
    public Integer delete(Object id) {
        return baseMapper.deleteByPrimaryKey(id);
    }


    @Override
    public Integer update(Users model) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", model.getUserId());
        return baseMapper.updateByExampleSelective(model, example);
    }

    public Users selectUserById(Integer id) {
        Users users = new Users();
        users.setUserId(id);
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
