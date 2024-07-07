package com.wu1015.DAO;

import redis.clients.jedis.Jedis;

public class RedisJava {

    private final static String host = "localhost";
    private final static String password = "123456";
    private final static int post=6379;
    private final Jedis jedis;

    public RedisJava() {
        jedis=new Jedis(host,post);
        try{
             jedis.auth("123456");
        }catch (Exception e){

        }
        // 没密码直接注释
    }
    public Jedis getJedis(){
        return jedis;
    }
}
