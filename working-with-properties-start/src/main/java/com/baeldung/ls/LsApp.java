package com.baeldung.ls;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.service.IProjectService;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class LsApp {

    @Autowired
    private IProjectService projectService;

    public static void main(final String... args) {
        SpringApplication.run(LsApp.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        projectService.save(new Project(1L, "My First Project", LocalDate.now()));
    }
}
