package com.kinsa.rest.model;


import java.util.concurrent.atomic.AtomicLong;

public class CoffeeShop {

    // id, name, address, latitude, longitude
    private long    id;
    private String  name;
    private String  address;
    private double latitude;
    private double longitude;

    private static final AtomicLong counter = new AtomicLong(100); // 100 as initial value

    public CoffeeShop() {};

    public CoffeeShop(Long iID,String iName, String iAddress,double iLatitude, double iLongitude ) {
        id          = iID;
        name        = iName;
        address     = iAddress;
        latitude    = iLatitude;
        longitude   = iLongitude;

    }

    public CoffeeShop(String iName, String iAddress,double iLatitude, double iLongitude ) {
        id          = CoffeeShop.counter.getAndIncrement();
        name        = iName;
        address     = iAddress;
        latitude    = iLatitude;
        longitude   = iLongitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString(){
        return "ID: " + id
                + " Name: " + name
                + " Address: " + address+ "\n"
                + " Latitude: " + latitude+ "\n"
                + " Longitude: " + longitude;
    }
}
