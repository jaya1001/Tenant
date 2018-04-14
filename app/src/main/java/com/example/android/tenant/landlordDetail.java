package com.example.android.tenant;

public class landlordDetail {
    private String name = "";
    private int age;
    private String gender = "";
    private String occupation = "";
    private long contact;
    private String email;
    private int type=1;

    public landlordDetail(){

    }


    public landlordDetail(String name, int age, String gender, String occupation, long contact, String email, int type) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.contact = contact;
        this.email = email;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public long getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public int getType() { return type; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
