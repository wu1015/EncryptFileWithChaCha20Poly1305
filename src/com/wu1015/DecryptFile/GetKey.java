package com.wu1015.DecryptFile;

import com.wu1015.DAO.RedisJava;
import redis.clients.jedis.Jedis;

public class GetKey {
    private final Jedis jedis;

    public GetKey() {
        RedisJava redisJava=new RedisJava();
        jedis=redisJava.getJedis();
    }
    public String getKey(String input){
        return jedis.get(input);
    }
}
