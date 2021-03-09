package com.shoes.ecom.dto;

public class ScheduleDto {
    private Integer id;
    private Integer sid;
    private Integer uid;
    private Integer hourU;
    private Integer hourL;
    private Integer available;


    private String phNo;
    private String name;
    private Integer rating;

    private Integer cost;
    private String address;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getHourU() {
        return hourU;
    }

    public void setHourU(Integer hourU) {
        this.hourU = hourU;
    }

    public Integer getHourL() {
        return hourL;
    }

    public void setHourL(Integer hourL) {
        this.hourL = hourL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }




}
