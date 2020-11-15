package com.shoes.ecom.po;

import javax.persistence.*;

@Entity
@Table(name ="user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;
    @Column(name="NAME")
    private String name;
    @Column(name="USER_NAME")
    private String userName;
    @Column(name="PH_NO")
    private String phNo;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="AADHAR")
    private Long aadhar;
    @Column(name="LOGGED_IN", insertable = false)
    private Character loggedIn;
    @Column(name ="SERVICE_ID")
    private Integer serviceId;
    @Column(name ="COST")
    private Integer cost;

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Long getAadhar() {
        return aadhar;
    }

    public void setAadhar(Long aadhar) {
        this.aadhar = aadhar;
    }





    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public Character getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Character loggedIn) {
        this.loggedIn = loggedIn;
    }





}
