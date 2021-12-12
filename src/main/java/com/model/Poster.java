package com.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Poster {
    private int id;
    private  int movieId;
    private String posterLink;
}
