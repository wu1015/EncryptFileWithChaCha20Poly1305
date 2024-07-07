package com.wu1015.EncryptFile;

import com.wu1015.DAO.RedisJava;
import com.wu1015.DelRecord.DelRecord;
import redis.clients.jedis.Jedis;

public class AddKey {
    private final Jedis jedis;

    public AddKey() {
        RedisJava redisJava=new RedisJava();
        jedis = redisJava.getJedis();
    }

    private boolean getMatch(String str){
        try{
            if (!jedis.get(str).isEmpty()) {
                return true;
            }
        }catch (Exception e) {
            return false;
        }
        return false;
    }
    public boolean addKey(String input,String output, String key){
        if(!getMatch(output)){
            String tmp=jedis.get(input);
            jedis.set(input,output);
            jedis.set(output,key);
            DelRecord delRecord=new DelRecord();
            delRecord.delRecordByString(tmp);
            return true;
        }else {
            return false;
        }
    }
}
