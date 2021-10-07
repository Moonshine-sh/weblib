package com.WebLib.domain;

import javax.persistence.*;

@Entity
@Table(name="readers")
public class Reader {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="mob_number")
    private String mobNumber;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public Reader() {
    }

    public Reader(String name, String surname, String mobNumber, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.mobNumber = mobNumber;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public void setMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
    }
}
