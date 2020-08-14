package com.wht.demo.redis;

import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * desc.
 *
 * @author wht
 */
public class DataTypesDemo {

    static Jedis jedis = new Jedis("127.0.0.1", 6379);
    static String listName = "myList";
    static String listName2 = "myList2";

    public void lists(){
        jedis.lpush(listName, "1", "2", "3", "4");
        jedis.lpush(listName2, "1", "2", "3", "4");

        String lpop = jedis.lpop(listName);


        List<String> list = jedis.lrange(listName, 0, -1);

        List<String> list1 = jedis.blpop(listName, "0", listName2, "0");

        jedis.del(listName);
        jedis.del(listName2);
    }

    public void strings(){
        jedis.set("key","val");
        jedis.setex("key",10,"new val");

        String s = jedis.get("key");
        System.out.println(s);

        //try {
        //    Thread.sleep(10 * 1000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        //s = jedis.get("key");
        //System.out.println(s);

        //String info = jedis.info();
        //System.out.println(info);

        System.out.println(jedis.info("memory"));
    }

    public void sets(){
        jedis.sadd("key","members");

        Set<String> set = jedis.smembers("key");

        System.out.println(Arrays.toString(set.toArray()));

        jedis.srem("key","members");

        Set<String> set2 = jedis.smembers("key");
        System.out.println(Arrays.toString(set2.toArray()));
    }

    public void zSets(){
        String key = "zSets";
        jedis.zadd(key,0,"alan");
        jedis.zadd(key,0,"ab");
        jedis.zadd(key,0,"ac");
        jedis.zadd(key,0,"ad");
        jedis.zadd(key,0,"ae");
        jedis.zadd(key,0,"af");
        jedis.zadd(key,0,"ba");
        jedis.zadd(key,0,"bc");
        jedis.zadd(key,0,"bd");
        jedis.zadd(key,0,"ca");
        jedis.zadd(key,0,"cb");
        jedis.zadd(key,0,"cc");
        jedis.zadd(key,0,"cd");
        jedis.zadd(key,0,"ce");

        Set<String> set = jedis.zrange(key, 0, -1);
        System.out.println(Arrays.toString(set.toArray()));


        Set<String> set1 = jedis.zrangeByLex(key, "[b", "[c");
        System.out.println(Arrays.toString(set1.toArray()));


        jedis.del(key);

    }

    public static void main(String[] args) {
        DataTypesDemo demo = new DataTypesDemo();

        demo.strings();


        jedis.close();
    }

    public static class FooBar{
        public long id;
        public String name;
        public String desc;
        public int age;

        public FooBar() {
        }

        public FooBar(long id, String name, String desc, int age) {
            this.id = id;
            this.name = name;
            this.desc = desc;
            this.age = age;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
