package com.baeldung.ls.persistence.repository;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baeldung.ls.persistence.model.Project;

@SpringBootTest
public class ProjectRepositoryIntegrationTest {

    @Autowired
    IProjectRepository projectRepository;

    @Test
    public void whenSavingNewProject_thenSuccess() {
        Project newProject = new Project(randomAlphabetic(6), LocalDate.now());

        assertNotNull(projectRepository.save(newProject));
    }

    @Test
    public void givenProject_whenFindById_thenSuccess() {
        Project newProject = new Project(randomAlphabetic(6), LocalDate.now());
        projectRepository.save(newProject);

        Optional<Project> retreivedProject = projectRepository.findById(newProject.getId());

        assertEquals(retreivedProject.get(), newProject);
    }

    @Test
    public void givenProject_whenFindByName_thenSuccess() {
        Project newProject = new Project(randomAlphabetic(6), LocalDate.now());
        projectRepository.save(newProject);

        Optional<Project> retreivedProject = projectRepository.findByName(newProject.getName());

        assertEquals(retreivedProject.get(), newProject);
    }

    @Test
    public void givenProjectCreated_whenFindByDateCreatedBetween_thenSuccess() {
        var oldProject = new Project(randomAlphabetic(6), LocalDate.now().minusYears(1));
        projectRepository.save(oldProject);

        var newProject = new Project(randomAlphabetic(6), LocalDate.now());
        projectRepository.save(newProject);

        var newProject2 = new Project(randomAlphabetic(6), LocalDate.now());
        projectRepository.save(newProject2);

        var retrievedProjects = projectRepository.findByDateCreatedBetween(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1)
        );

        assertThat(retrievedProjects, hasItems(newProject, newProject2));
    }
}