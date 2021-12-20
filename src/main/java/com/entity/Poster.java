package com.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class Poster {
    private int id;
    private  int movieId;
    private String posterLink;
}
