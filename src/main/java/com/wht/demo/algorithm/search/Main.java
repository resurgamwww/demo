package com.wht.demo.algorithm.search;

/**
 * @author wanghtw
 * @date 2020/3/12 16:04
 */
public class Main {
    private static final String[] excepts = {"/outdata/vrp/BmVrpDoAction_receviceTmsBills.action", "/app/api.action"};
    private static final String s1 = "/outdata/vrp/BmVrpDoAction_receviceTmsBills.action";
    private static final String  s2 = "/app/api.action";

    public static void main(String[] args) {
        long l = System.nanoTime();

        String path = "test";


        System.out.println(System.nanoTime() - l);
    }

    public static boolean isExists(String s){
        //现在数据量小，所以先简单处理下，如果后续有更多例外项，换搜索算法
        if (s1.endsWith(s) || s2.endsWith(s)){
            return true;
        }

        return false;
    }
}
