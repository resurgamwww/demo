package com.wht.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.util.Slowlog;

import java.util.List;
import java.util.UUID;

/**
 * desc.
 *
 * @author wht
 */
public class LockDemo {
    public static void main(String[] args) {
        Jedis jedis = DataTypesDemo.jedis;

        String key = "key";
        String value = "value";

        jedis.eval("if (redis.call('setnx',KEYS[1],ARGV[1]) < 1) then return 0; end; redis.call('expire',KEYS[1],tonumber(ARGV[2])); return 1;", 1, key, value, "100");


        //加锁
        String uuid = UUID.randomUUID().toString();
        jedis.set(key,uuid,new SetParams().nx().ex(30));

        //解锁
        jedis.eval("if (redis.call('get',KEYS[1]) == ARGV[1]) then return redis.call('del',KEYS[1]) else return 0 ;end;", 1, key, uuid);

        List<Slowlog> slowLogs = jedis.slowlogGet();

    }
}
