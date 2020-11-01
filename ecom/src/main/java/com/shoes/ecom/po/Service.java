package com.shoes.ecom.po;

import javax.persistence.*;


@Entity
@Table(name ="service")
public class Service {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;
    @Column(name="S_NAME")
    private String sname;

    @Column(name="HOURS")
    private Integer hours;
    @Column(name="TIME_U")
    private Integer timeU;
    @Column(name="TIME_L")
    private Integer timeL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getTimeU() {
        return timeU;
    }

    public void setTimeU(Integer timeU) {
        this.timeU = timeU;
    }

    public Integer getTimeL() {
        return timeL;
    }

    public void setTimeL(Integer timeL) {
        this.timeL = timeL;
    }
}
