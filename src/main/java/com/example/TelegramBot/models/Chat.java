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
    private int numQuestion;

    public Chat(long id, String firstName, String lastName, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        numQuestion = 0;
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

    public int getNumQuestion() {
        return numQuestion;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
    }

    public void setP1(Integer p1) {
        this.p1 = p1;
    }

    public void setP2(Integer p2) {
        this.p2 = p2;
    }

    public void setP3(Integer p3) {
        this.p3 = p3;
    }

    public void setP4(Integer p4) {
        this.p4 = p4;
    }

    public void setP5(Integer p5) {
        this.p5 = p5;
    }

    public void setP6(Integer p6) {
        this.p6 = p6;
    }

    public void setP7(Integer p7) {
        this.p7 = p7;
    }

    public void setP8(Integer p8) {
        this.p8 = p8;
    }

}

