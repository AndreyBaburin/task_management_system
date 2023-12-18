package com.andrey_baburin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

    @RequestMapping({"", "/", "home_page", "home_page.html"})
    public String homePage() {
        return "home_page";
    }
}
