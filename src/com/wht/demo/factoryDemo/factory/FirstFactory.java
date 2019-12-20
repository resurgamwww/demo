package com.wht.demo.factoryDemo.factory;

import com.wht.demo.factoryDemo.dto.Dto;
import com.wht.demo.factoryDemo.dto.FirstDto;

/**
 * @author wanghtw
 * @date 2019/11/6 16:09
 */
public class FirstFactory implements DtoFactory{

    @Override
    public Dto getDto() {
        return new FirstDto();
    }
}
