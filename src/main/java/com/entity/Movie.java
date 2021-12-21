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
    private List<Country> country;

    @Column(name = "genre")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Genre> genre;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private double rating;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "picturePath")
    private String picturePath;

    @Column(name = "review")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> review;
}
