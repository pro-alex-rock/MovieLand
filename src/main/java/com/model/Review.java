package com.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Review {
    private  int id;
    private  int userId;
    private  int movieId;
    private String review;
}
