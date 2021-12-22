package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @Column(name = "name_russian")
    private String nameRussian;
    @Column(name = "name_native")
    private String nameNative;
    @Column(name = "year")
    private int yearOfRelease;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name =" country")
    @JoinColumn(name = "country_id", insertable=false, updatable=false)
    private List<Country> countries;

    @Column(name = "genre")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", insertable=false, updatable=false)
    private List<Genre> genres;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private double rating;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "picturePath")
    private String picturePath;

    @Column(name = "review")
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    public void addCountry(Country cntr) {
        countries.add(cntr);
    }

    public void removeCountry(Country cntr) {
        countries.remove(cntr);
        cntr.setCountry(null);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setMovie(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setMovie(null);
    }

    public String getNameRussian() {
        return nameRussian;
    }

    public void setNameRussian(String nameRussian) {
        this.nameRussian = nameRussian;
    }

    public String getNameNative() {
        return nameNative;
    }

    public void setNameNative(String nameNative) {
        this.nameNative = nameNative;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> country) {
        this.countries = country;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genre) {
        this.genres = genre;
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

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
