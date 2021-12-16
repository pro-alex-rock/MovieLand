package com.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class User {
    private int id;
    private String name;
    private String password;
    private String sole;
    private Role role;
}
