package com.example.TelegramBot.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat2")
public class Chat2 {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private Integer p1;
    private Integer p2;
    private Integer p3;
    private int numQuestion;

    public Chat2(long id, String firstName, String lastName, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        numQuestion = 0;
    }

    public Chat2() {
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

}
