package com.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
public class MovieDto {
    private int id;
    private String titleRussianMovie;
    private String titleNativeMovie;
    private int yearOfRelease;
    private double rating;
    private BigDecimal price;
    private String picturePath;
}
