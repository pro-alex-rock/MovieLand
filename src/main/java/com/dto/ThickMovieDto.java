package com.dto;

import com.entity.Country;
import com.entity.Genre;
import com.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThickMovieDto {

    private int id;
    private String nameRussian;
    private String nameNative;
    private int yearOfRelease;
    private List<Country> country;
    private List<Genre> genre;
    private String description;
    private double rating;
    private BigDecimal price;
    private String picturePath;
    private List<Review> reviews;
}
