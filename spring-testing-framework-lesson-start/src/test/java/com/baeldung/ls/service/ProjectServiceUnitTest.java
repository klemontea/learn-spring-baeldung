package com.baeldung.ls.service;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.repository.IProjectRepository;
import com.baeldung.ls.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ProjectServiceUnitTest {

    @Mock
    IProjectRepository projectRepository;
    @InjectMocks
    ProjectServiceImpl projectService;

    private AutoCloseable closeable;

    @BeforeEach
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void whenSavingProject_thenOK() {
        Project project = new Project("name", LocalDate.now());
        when(projectRepository.save(project)).thenReturn(project);

        var saved = projectService.save(project);

        assertNotNull(saved);
    }
}
