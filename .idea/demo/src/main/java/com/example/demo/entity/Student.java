package com.example.demo.entity;

public class Student {
    private String id;

    private String name;

    private String studentid;

    public Student(String id, String name, String studentid) {
        this.id = id;
        this.name = name;
        this.studentid = studentid;
    }

    public Student() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }
}