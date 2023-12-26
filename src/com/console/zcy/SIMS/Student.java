package com.console.zcy.SIMS;

public class Student {
    private int id;
    private String name;
    private Integer age;
    private String address;

    public Student() {}
    public Student(int stuId, String stuName) {
        this.id = stuId;
        this.name = stuName;
        this.age = null;
        this.address = null;
    }

    public Student(int stuId, String stuName, int stuAge) {
        this.id = stuId;
        this.name = stuName;
        this.age = stuAge;
        this.address = null;
    }

    public Student(int stuId, String stuName, int stuAge, String stuAddress) {
        this.id = stuId;
        this.name = stuName;
        this.age = stuAge;
        this.address = stuAddress;
    }

    public void setID(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getAddress() {
        return this.address;
    }

}
