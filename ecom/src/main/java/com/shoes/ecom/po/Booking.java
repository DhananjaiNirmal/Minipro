package com.shoes.ecom.po;

import javax.persistence.*;
import java.io.CharArrayReader;

@Entity
@Table(name ="booking")
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "S_ID")
    private Integer sid;
    @Column(name = "S_UID")
    private Integer suid;

    @Column(name = "HOUR_L")
    private Integer hourl;

    @Column(name = "HOUR_U")
    private Integer houru;

    @Column(name = "STATUS")
    private Character status;


    public Integer getId() {
        return id;
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

    public Integer getSuid() {
        return suid;
    }

    public void setSuid(Integer suid) {
        this.suid = suid;
    }

    public Integer getHourl() {
        return hourl;
    }

    public void setHourl(Integer hourl) {
        this.hourl = hourl;
    }

    public Integer getHouru() {
        return houru;
    }

    public void setHouru(Integer houru) {
        this.houru = houru;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }





    }
