package com.asml.ideap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource{
    @GetMapping("/")
    public String index(){
        return "Home Page";

    }
    @GetMapping("/ideap")
    public String indexP(){
        return "Idea Page";

    }
}