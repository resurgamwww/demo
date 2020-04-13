package com.wht.demo.factoryDemo.dto;

/**
 * Dto 接口
 *
 * @author wanghtw
 * @date 2019/11/6 14:01
 */
public interface Dto {

    default Dto getInstance(Param param) throws Exception {
        throw new Exception("必须先实现");
    }

    static Dto getStatic(String type) throws Exception {
        DtoTypeEnum typeEnum = DtoTypeEnum.getByCode(type);
        Class clazz = typeEnum.getClazz();
        Dto dto1 = (Dto)clazz.newInstance();
        return dto1;
    }

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getExt();

    void setExt(String ext);

    Long getMaterialId();

    void setMaterialId(Long materialId);

}
