package com.shoes.ecom.dto;

public class BookingAlter {
    private Integer id;
    private Character status;


    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
