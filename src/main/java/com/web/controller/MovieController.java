package com.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/", "/movies"})
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
}
