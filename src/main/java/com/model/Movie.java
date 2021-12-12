package com.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
public class Movie {
    private int id;
    private String titleRussianMovie;
    private String titleNativeMovie;
    private int yearOfRelease;
    private String country;
    private Genre genre;
    private String description;
    private double rating;
    private BigDecimal price;
    private String posterLink;

    public String getTitleRussianMovie() {
        return titleRussianMovie;
    }

    public void setTitleRussianMovie(String titleRussianMovie) {
        this.titleRussianMovie = titleRussianMovie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleNativeMovie() {
        return titleNativeMovie;
    }

    public void setTitleNativeMovie(String titleNativeMovie) {
        this.titleNativeMovie = titleNativeMovie;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }
}
