package com.wht.demo.factoryDemo.service;

import com.wht.demo.factoryDemo.dto.Dto;
import com.wht.demo.factoryDemo.dto.DtoTypeEnum;
import com.wht.demo.factoryDemo.dto.Param;
import com.wht.demo.factoryDemo.factory.DtoFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author wanghtw
 * @date 2019/11/6 14:06
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Integer[] array = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> list = Arrays.asList(array);
        Collections.shuffle(list);
        int i = Collections.binarySearch(list, Integer.valueOf(1));
        printList(list);
        System.out.println(i);

        //Collections.rotate(list,4);

        //Main main = new Main();
        //List<Object> first = main.query(new Param(123L, "123", "123", 123L, "third"));
    }

    private static void printList(List list) {
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }

    public List<Object> query(Param param) throws Exception {
        Dto dto = Dto.getStatic(param.getType());
        dto.getInstance(param);
        return commonQuery(dto);
    }

    public List<Object> queryWithFactory(Param param) throws Exception {
        DtoTypeEnum dtoTypeEnum = DtoTypeEnum.getByCode(param.getType());
        Object o = dtoTypeEnum.getClazz().newInstance();
        DtoFactory factory = (DtoFactory) o;
        Dto dto1 = factory.getDto();
        Dto dto = Dto.getStatic(param.getType());
        dto.getInstance(param);
        return commonQuery(dto);
    }

    private List<Object> commonQuery(Dto dto) {
        return Collections.emptyList();
    }

    public Integer[] getArray(int length) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
        }
        Collections.shuffle(null, null);
        Random random = new Random();
        random.nextInt();
        return array;
    }


}
