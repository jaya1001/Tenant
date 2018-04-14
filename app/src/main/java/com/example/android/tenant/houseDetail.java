package com.example.android.tenant;

public class houseDetail {

    private String location;
    private int noOfPersons;
    private String bhk;
    private String rentRange;
    private String parking;
    private String email;

    public houseDetail(String location, int noOfPersons, String bhk, String rentRange, String parking, String email) {
        this.location = location;
        this.noOfPersons = noOfPersons;
        this.bhk = bhk;
        this.rentRange = rentRange;
        this.parking = parking;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public int getNoOfPersons() {
        return noOfPersons;
    }

    public String getBhk() {
        return bhk;
    }

    public String getRentRange() {
        return rentRange;
    }

    public String getParking() {
        return parking;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNoOfPersons(int noOfPersons) {
        this.noOfPersons = noOfPersons;
    }

    public void setBhk(String bhk) {
        this.bhk = bhk;
    }

    public void setRentRange(String rentRange) {
        this.rentRange = rentRange;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }
}
