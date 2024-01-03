package com.example.investotrack.dataprovidercore.model;

import java.util.Optional;

public class Attribution {

    private String brand;
    private String url;

    /**
     * Constructor.
     *
     * @param brand the company brand name
     * @param url   the url to the company website, or null
     */
    public Attribution(String brand, String url) {
        this.brand = brand;
        this.url = url;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
