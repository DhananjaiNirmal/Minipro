package com.shoes.ecom.po;

import javax.persistence.*;

@Entity
@Table(name ="schedule")
public class Schedule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "S_ID")

    private Integer sid;
    @Column(name="U_ID")
    private Integer uid;
    @Column(name = "HOUR_U")
    private Integer hourU;
    @Column(name = "HOUR_L")
    private Integer hourL;
    @Column(name = "AVAILABLE")
    private Integer available;


    public Integer getId() {
        return id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}