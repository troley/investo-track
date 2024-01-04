package com.example.investotrack.webapp.viewmodel;

import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.Optional;

public class AttributionViewModel {

    private String brand;
    private String url;

    public AttributionViewModel(String brand, @Nullable String url) {
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

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttributionViewModel viewModel = (AttributionViewModel) o;
        return Objects.equals(brand, viewModel.brand) && Objects.equals(url, viewModel.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, url);
    }
}
