package com.wht.demo.factoryDemo.factory;

import com.wht.demo.factoryDemo.dto.Dto;

/**
 * @author wanghtw
 * @date 2019/11/6 16:08
 */
public interface DtoFactory {
    /**
     * 获取Dto实例
     *
     * @return
     */
    Dto getDto();
}
