package com.baeldung.ls.client;

import com.baeldung.ls.web.dto.ProjectDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProjectRestApiLiveTest {

    private static final String BASE_URL = "http://localhost:8080/projects";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void givenProjectExists_whenGet_thenSuccess() {
        var response = restTemplate.getForEntity(BASE_URL.concat("/1"), ProjectDto.class);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    public void givenNewProject_whenCreate_thenSuccess() {
        var newProject = new ProjectDto(null, "Test Project", LocalDate.now());

        var response = restTemplate.postForEntity(BASE_URL, newProject, Void.class);

        assertEquals(200, response.getStatusCode().value());
    }
}
