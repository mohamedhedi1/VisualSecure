package com.mohamedhedi1.VisualSecure.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/SpringBootProject")
public class SpringBootProjectGenerator {
    @GetMapping("/create")
    public ModelAndView redirectToLink() {
        String urlToRedirect = "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.1.3&baseDir=demo&groupId=com.example&artifactId=demo&name=hedi&description=Demo%20project%20for%20Spring%20Boot&packageName=com.example.demo&packaging=jar&javaVersion=17";

        return new ModelAndView(new RedirectView(urlToRedirect));
    }

}
