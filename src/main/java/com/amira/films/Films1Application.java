package com.amira.films;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.amira.films.service.FilmService;
import com.amira.films.service.ScenaristeService;

@SpringBootApplication
//@EnableJpaRepositories("com.amira.films.reposit","")

public class Films1Application {
@Autowired
FilmService filmService;
@Autowired
ScenaristeService scService;
public static void main(String[] args) {
SpringApplication.run(Films1Application.class, args);
}

}