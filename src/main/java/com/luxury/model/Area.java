package com.luxury.model;

public class Area {
    /**
     * id
     */
    private Integer id;

    /**
     * 区域编号
     */
    private String areaId;

    /**
     * 省份ID
     */
    private Integer province;

    /**
     * 省份名字
     */
    private String provinceName;

    /**
     * 城市ID
     */
    private Integer city;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 地区ID
     */
    private Integer district;

    /**
     * 地区
     */
    private String districtName;

    /**
     * 状态（0=禁用；1=启用）
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}