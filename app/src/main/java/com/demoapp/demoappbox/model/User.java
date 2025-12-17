package com.demoapp.demoappbox.model;

public class User {
  //  private int id;
    private String name;
    private String roll_number;
    private String subject;

    public User(String name, String roll, String subject) {
        this.name = name;
        this.roll_number = roll;
        this.subject = subject;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
