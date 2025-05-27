package com.tuk.app0505;

public class Restaurant {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private String features;
    private String image;

    public Restaurant(String name, double latitude, double longitude, String features, String image) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.features = features;
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public void setImageUrl(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getFeatures() {
        return features;
    }

    public String getImage() {
        return image;
    }
}
