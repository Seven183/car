package car.controller;

import car.entity.Users;
import car.service.UsersService;
import car.utils.RedisUtils;
import car.utils.Result;
import car.utils.SystemException;
import car.utils.SystemSuccess;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;


@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UsersService usersService;

    @PostMapping(value="/login")
    public Result<JSONObject> login(@RequestBody Users user){
        Users queryByParam = usersService.login(user);
        if(queryByParam==null) {
            return Result.failed(SystemException.UNKNOWN_USER.getCode(), SystemException.UNKNOWN_USER.getMessage());
        }
        Jedis jedis = RedisUtils.getConn();
        String token = jedis.get("auth:token");
        jedis.set("auth:token", token, "NX","EX",3600);
        JSONObject json = new JSONObject();
        json.put("name",user.getUserName());
        json.put("userId",queryByParam.getUserId());
        json.put("token",token);
        json.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        json.put("message", SystemSuccess.LOGIN_SUCCESS.getMessage());
        return Result.ok(json);
    }

    @GetMapping(value="/info")
    public Result<JSONObject> info(String token){
        Jedis jedis = RedisUtils.getConn();
        String redisToken = jedis.get("auth:token");
        if(token.equals(redisToken)){
            JSONObject json = new JSONObject();
            json.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            json.put("message", SystemSuccess.TOKEN_AUTH_SUCCESS.getMessage());
            return Result.ok(json);
        }
        return Result.failed(SystemException.TOKEN_AUTH_FAILED.getCode(), SystemException.TOKEN_AUTH_FAILED.getMessage());
    }

    @PostMapping(value="/loginOut")
    public Result<String> loginOut() {
        Integer users = usersService.loginOut();
        return Result.ok(users == 1 ? SystemSuccess.LOGIN_OUT_SUCCESS.getMessage() : SystemException.LOGIN_OUT_FAILED.getMessage());
    }
}
