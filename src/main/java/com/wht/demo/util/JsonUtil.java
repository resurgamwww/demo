package com.wht.demo.util;

import com.google.gson.JsonObject;

import javax.annotation.Nonnull;

/**
 * desc.
 *
 * @author Wang Haitong
 * @date 2020/9/8 13:46
 */
public class JsonUtil {

    /**
     * 将下列格式的字符串解析为json
     *
     * pageQuery.page:1
     * pageQuery.limit:3
     * wayBillId:CC202009050006
     *
     * @param s
     * @return
     */
    public static JsonObject parseFromKVString(@Nonnull String s){
        String[] split = s.split("\n");
        JsonObject object = new JsonObject();

        //todo Wang Haitong 2020/9/8 13:49

        return null;
    }
}
