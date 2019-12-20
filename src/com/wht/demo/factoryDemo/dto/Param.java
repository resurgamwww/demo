package com.wht.demo.factoryDemo.dto;

/**
 * 查询参数
 *
 * @author wanghtw
 * @date 2019/11/6 14:10
 */
public class Param {
    private Long id;
    private String name;
    private String ext;

    private Long materialId;
    private String type;


    public Param() {
    }

    public Param(Long id, String name, String ext, Long materialId, String type) {
        this.id = id;
        this.name = name;
        this.ext = ext;
        this.materialId = materialId;
        this.type = type;
    }

    public Param(Long id, String name, String ext, Long materialId) {
        this.id = id;
        this.name = name;
        this.ext = ext;
        this.materialId = materialId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
}
