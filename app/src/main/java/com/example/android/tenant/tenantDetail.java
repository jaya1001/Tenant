package com.example.android.tenant;
/**
 * Created by User on 3/31/2018.
 */

public class tenantDetail {

    private String name = "";
    private int age;
    private String gender = "";
    private String occupation = "";
    private long contact;
    private String email;
    private String marital_status = "";

    public tenantDetail(String name, int age, String gender, String occupation, long contact, String email, String marital_status) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.contact = contact;
        this.email = email;
        this.marital_status = marital_status;
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

    public String getMarital_status() {
        return marital_status;
    }

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

    public void setContact(int contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }
}
