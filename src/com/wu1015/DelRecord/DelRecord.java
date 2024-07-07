package com.wu1015.DelRecord;

import com.wu1015.DAO.RedisJava;
import org.apache.commons.codec.digest.DigestUtils;
import redis.clients.jedis.Jedis;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DelRecord {
    private final Jedis jedis;

    public DelRecord() {
        RedisJava redisJava=new RedisJava();
        jedis=redisJava.getJedis();
    }
    public boolean delRecordByString(String input){
        try {
            String output=jedis.get(input);
            jedis.del(input);
            jedis.del(output);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean delRecordByPath(String fullPath){
        try {
            byte[] originFile = Files.readAllBytes(Paths.get(fullPath));
            String input= DigestUtils.md5Hex(originFile);
            String output=jedis.get(input);
            jedis.del(input);
            jedis.del(output);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
