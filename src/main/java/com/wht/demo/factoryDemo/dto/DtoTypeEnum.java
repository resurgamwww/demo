package com.wht.demo.factoryDemo.dto;

/**
 * Dto类型映射
 *
 * @author wanghtw
 * @date 2019/11/6 14:08
 */
public enum DtoTypeEnum {
    FIRST("first", FirstDto.class),
    SECOND("second", SecondDto.class),
    THIRD("third", ThirdDto.class)
    ;

    private String code;
    private Class clazz;

    DtoTypeEnum(String code, Class clazz) {
        this.code = code;
        this.clazz = clazz;
    }

    public static DtoTypeEnum getByCode(String code) throws Exception {
        for (DtoTypeEnum value : DtoTypeEnum.values()) {
            if (value.getCode().equals(code)){
                return value;
            }
        }
        throw new Exception("Illegal argument!");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public static Object getDto(Param param) throws Exception {
        String type = param.getType();
        return getByCode(type).getClazz().newInstance();
    }
}
