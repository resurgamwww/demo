package com.wht.demo.factoryDemo.dto;

/**
 * 第二个Dto
 * @author wanghtw
 * @date 2019/11/6 14:04
 */
public class SecondDto implements Dto {
    private Long id;
    private String name;
    private String ext;

    private Long custId;
    private Long materialId;

    @Override
    public Dto getInstance(Param param) {
        SecondDto dto = new SecondDto();
        //设置参数......
        dto.setName(param.getName());
        dto.setCustId(param.getId());
        dto.setMaterialId(param.getMaterialId());

        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
}
