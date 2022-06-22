package com.example.TelegramBot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private Integer p1;
    private Integer p2;
    private Integer p3;
    private Integer p4;
    private Integer p5;
    private Integer p6;
    private Integer p7;
    private Integer p8;

    public Chat(long id, String firstName, String lastName, String userName, Integer p1, Integer p2, Integer p3, Integer p4, Integer p5, Integer p6, Integer p7, Integer p8) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.p7 = p7;
        this.p8 = p8;
    }

    public Chat() {
        // EMPTY
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getP1() {
        return p1;
    }

    public Integer getP2() {
        return p2;
    }

    public Integer getP3() {
        return p3;
    }

    public Integer getP4() {
        return p4;
    }

    public Integer getP5() {
        return p5;
    }

    public Integer getP6() {
        return p6;
    }

    public Integer getP7() {
        return p7;
    }

    public Integer getP8() {
        return p8;
    }
}

