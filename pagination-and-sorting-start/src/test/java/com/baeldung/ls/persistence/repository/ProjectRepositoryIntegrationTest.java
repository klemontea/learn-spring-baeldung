package com.baeldung.ls.persistence.repository;

import com.baeldung.ls.persistence.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProjectRepositoryIntegrationTest {

    @Autowired
    IProjectRepository projectRepository;

    @Test
    public void givenDataCreated_whenFindAllPaginated_thenSuccess() {
        var retrievedProjects = projectRepository.findAll(PageRequest.of(0, 2));

        assertEquals(2, retrievedProjects.getContent().size());
    }

    @Test
    public void givenDataCreated_whenFindAllSorted_thenSuccess() {
        var retrievedProjects = (List<Project>) projectRepository.findAll(
                Sort.by(Sort.Order.asc("name"))
        );

        var sortedProjects = retrievedProjects.stream().collect(Collectors.toList());
        sortedProjects.sort(Comparator.comparing(Project::getName));

        assertEquals(sortedProjects, retrievedProjects);
    }

    @Test
    public void givenDataCreated_whenFindAllPaginatedAndSorted_thenSuccess() {
        var retrievedProjects = projectRepository.findAll(
                PageRequest.of(
                        0,
                        2,
                        Sort.by(Sort.Order.asc("name"))
                )
        );

        assertEquals(2, retrievedProjects.getContent().size());
    }
}