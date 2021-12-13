package com.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
public class MovieDto {
    private int id;
    private String titleRussian;
    private String titleNative;
    private int yearOfRelease;
    private double rating;
    private BigDecimal price;
    private String picturePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleRussian() {
        return titleRussian;
    }

    public void setTitleRussian(String titleRussian) {
        this.titleRussian = titleRussian;
    }

    public String getTitleNative() {
        return titleNative;
    }

    public void setTitleNative(String titleNative) {
        this.titleNative = titleNative;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
