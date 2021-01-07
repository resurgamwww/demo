package com.wht.demo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Temp {

/*

get /szjw_waybill_list/_search?_source=wayBillId,joinManagerId,lineSaleId,dutyManagerId
    {
        "from": 0,
            "size": 1000,
            "query": {
        "bool": {
            "must_not": {
                "bool": {
                    "should": [
                    {
                        "exists": {
                        "field": "joinManagerCity"
                    }
                    },
                    {
                        "exists": {
                        "field": "dutyManagerCity"
                    }
                    },
                    {
                        "exists": {
                        "field": "lineSaleCity"
                    }
                    }
          ]
                }
            }
        }
    }
    }

    */


    public static void main(String[] args) throws Exception {
        String runTestUrl = "http://szjw-domain-gateway.prod.yunniao.cn/api/es/v2/es/runtest/syncByRunId";
        String waybillUrl = "http://szjw-domain-elasticsearch-canal.prod.yunniao.cn/v2/es/waybill/batchDataSync";

        String runTestKey = "runTestId";
        String wayBillKey = "wayBillId";

//        call(runTestUrl, runTestKey, "/home/wanghaitong/文档/云鸟/r.txt");
//        call(waybillUrl, wayBillKey, "/home/wanghaitong/.deepinwine/Deepin-DingTalk/dosdevices/
//        c:/users/wanghaitong/Downloads/1.txt");

        repairCustomerBillingAcct();
    }

    private static void repairCustomerBillingAcct() {
        String url = "http://szjw-domain-waybill.prod.yunniao.cn/v2/customerBilling/amount/create?userId=2168";
        String ids = "KH202011180033,KH202012290024,KH202012290025,KH202012290026,KH202012290027,KH202012290028,KH202012290029,KH202012290030,KH202012290031,KH202012290032,KH202012290033,KH202012290034,KH202012290035,KH202012290036,KH202012290037,KH202012290038,KH202012290039,KH202012290040,KH202012290041,KH202012290042,KH202012290043,KH202012290044,KH202012290045,KH202012290046,KH202012290047,KH202012290048,KH202012290049,KH202012290050,KH202012290051,KH202012290052,KH202012290053,KH202012290054,KH202012290055,KH202012290056,KH202012290057,KH202012290058,KH202012290059,KH202012290060,KH202012290061,KH202012290062,KH202012290063,KH202012290064,KH202012290065,KH202012290066,KH202012290067,KH202012290068,KH202012290069,KH202012290070,KH202012290071,KH202012290072,KH202012290073,KH202012290074,KH202012290075,KH202012290076,KH202012290077,KH202012290078,KH202012290079,KH202012290080,KH202012290081,KH202012290082,KH202012290083,KH202012290084,KH202012290085,KH202012290086,KH202012290087,KH202012290089,KH202012290090,KH202012290091,KH202012290092,KH202012290093,KH202012290094,KH202012290095,KH202012290096,KH202012290097,KH202012290098,KH202012290099,KH202012290100,KH202012290101,KH202012290102,KH202012290103,KH202012290104,KH202012290105,KH202012290106,KH202012290107,KH202012290108,KH202012290109,KH202012290110,KH202012290111,KH202012290112,KH202012290113,KH202012290114,KH202012290115,KH202012290116,KH202012290117,KH202012290118,KH202012290119,KH202012290120,KH202012290121,KH202012290122,KH202012290123,KH202012290124,KH202012290125,KH202012290126,KH202012290127,KH202012290128,KH202012290129,KH202012290130,KH202012290131,KH202012290132,KH202012290133,KH202012290134,KH202012290135,KH202012290136,KH202012290137,KH202012290138,KH202012290139,KH202012290140,KH202012290141,KH202012290142,KH202012290143,KH202012290144,KH202012290145,KH202012290146,KH202012290147,KH202012290148,KH202012290149,KH202012290150,KH202012290151,KH202012290152,KH202012290153,KH202012290154,KH202012290155,KH202012290156,KH202012290157,KH202012290158,KH202012290159,KH202012290160,KH202012290161,KH202012290162,KH202012290163,KH202012290164,KH202012290165,KH202012290166,KH202012290167,KH202012290168,KH202012290169,KH202012290170,KH202012290171,KH202012290172,KH202012290173,KH202012290174,KH202012290175,KH202012300001,KH202012300002,KH202012300003,KH202012300004,KH202012300005,KH202012300006,KH202012300007,KH202012300008,KH202012300009,KH202012300010,KH202012300011,KH202012300012,KH202012300013,KH202012300014,KH202012300015,KH202012300016,KH202012300017,KH202012300018,KH202012300019,KH202012300020,KH202012300021,KH202012300022,KH202012300023,KH202012300024,KH202012300025,KH202012300026,KH202012300027,KH202012300028,KH202012300029,KH202012300030,KH202012300031,KH202012300032,KH202012300033,KH202012300034,KH202012300035,KH202012300036,KH202012300037,KH202012300038,KH202012300039,KH202012300040,KH202012300041,KH202012300042,KH202012300043,KH202012300044,KH202012300045,KH202012300046,KH202012300047,KH202012300048,KH202012300049,KH202012300050,KH202012300051,KH202012300052,KH202012300053,KH202012300054,KH202012300055,KH202012300056,KH202012300057,KH202012300058,KH202012300059,KH202012300060,KH202012300061,KH202012300062,KH202012300063,KH202012300064,KH202012300065,KH202012300066,KH202012300067,KH202012300068,KH202012300069,KH202012300070,KH202012300071,KH202012300072,KH202012300073,KH202012300074,KH202012300075,KH202012300076,KH202012300077,KH202012300078,KH202012300079,KH202012300080,KH202012300081,KH202012300082,KH202012300083,KH202012300084,KH202012300085,KH202012300086,KH202012300087,KH202012300088";
//        select user_id from szjw_waybill.customer_acct_book cab where user_id like '+?'
        String[] split = ids.split(",");
        for (String s : split) {
            String content = "{\"acctType\": 0,\"payPassword\": \"string\",\"userId\": \"" +
                    s +
                    "\"}";
            HttpRequest post = HttpUtil.createPost(url);
            post.body(content);
            HttpResponse response = post.execute();
            System.out.println(response.body());
        }
    }

    public static void call(String url, String key, String fileName) throws IOException, InterruptedException {
        File file = new File(fileName);
        byte[] content = new byte[Math.toIntExact(file.length())];
        FileInputStream in = new FileInputStream(file);
        in.read(content);
        in.close();

        String s = new String(content);
        JSONObject o = JSONObject.parseObject(s);
        JSONObject hits = o.getJSONObject("hits");
        JSONArray array = hits.getJSONArray("hits");

        ArrayList<String> list = new ArrayList<>();
        HashSet<String> ids = new HashSet<>();
        int count = 0;

        HashSet<String> dutyManagerIds = new HashSet<>();
        for (Object item : array) {
            if (item instanceof JSONObject) {
                count++;
                JSONObject row = (JSONObject) item;
                JSONObject source = row.getJSONObject("_source");
                String id = source.getString(key);
                if (StringUtils.isEmpty(id)) {
                    continue;
                }
                list.add(id);

                String joinManagerId = source.getString("joinManagerId");
                String dutyManagerId = source.getString("dutyManagerId");
                String lineSaleId = source.getString("lineSaleId");
                HashSet<String> set = new HashSet<>();
                set.add(joinManagerId);
                set.add(dutyManagerId);
                set.add(lineSaleId);

                dutyManagerIds.add(dutyManagerId);

                for (String userId : set) {
                    if (!ids.contains(userId)) {
                        callUserApi(userId);
                        ids.add(userId);
                    }
                }

                if (count == 200) {
                    Thread.sleep(100);
                    post(url, list);
                    count = 0;
                    list.clear();
                }
            }
        }

        if (CollUtil.isNotEmpty(list)) {
            post(url, list);
        }

        System.out.println(dutyManagerIds);
    }

    private static void post(String url, ArrayList<String> list) {
        HttpRequest post = HttpUtil.createPost(url);
        post.body(JSON.toJSONString(list));
        HttpResponse response = post.execute();
        System.out.println(response.body());
    }

    private static void callUserApi(String userId) {
        HttpRequest post = HttpUtil.createPost("http://szjw-domain-base.prod.yunniao.cn/v1/base/user/cache/getUserBaseInfo");
        post.body(userId);
        HttpResponse response = post.execute();
        System.out.println(response.body());
    }
}
