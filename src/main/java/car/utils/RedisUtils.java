package car.utils;

import redis.clients.jedis.Jedis;

public class RedisUtils {

    public static Jedis getConn(){
        Jedis jedis = new Jedis(PropertyUtils.getStrValue("spring.redis.host"), PropertyUtils.getIntValue("spring.redis.port"));
        jedis.auth(PropertyUtils.getStrValue("spring.redis.password"));
        jedis.select(PropertyUtils.getIntValue("spring.redis.database"));
        return jedis;
    }
}
