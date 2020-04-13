package com.wht.demo.factoryDemo.dto;

/**
 * 第一个Dto
 *
 * @author wanghtw
 * @date 2019/11/6 14:02
 */
public class FirstDto implements Dto {
    private Long id;
    private String name;
    private String ext;

    private Long houseId;
    private Long materialId;

    @Override
    public Dto getInstance(Param param) {
        FirstDto dto = new FirstDto();
        //设置参数......
        dto.setName(param.getName());
        dto.setHouseId(param.getId());
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

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
}
