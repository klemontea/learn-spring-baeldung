package com.baeldung.ls.web.controller;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.service.IProjectService;
import com.baeldung.ls.web.dto.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    //

    @GetMapping(value = "/{id}")
    public ProjectDto findOne(@PathVariable Long id) {
        var entity = projectService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }

    @PostMapping
    public void create(@RequestBody ProjectDto newProject) {
        this.projectService.save(convertToEntity(newProject));
    }

    private ProjectDto convertToDto(Project entity) {
        return new ProjectDto(entity.getId(), entity.getName());
    }

    private Project convertToEntity(ProjectDto dto) {
        var project = new Project(dto.getName(), null);
        if (StringUtils.isEmpty(dto.getId())) {
            project.setId(dto.getId());
        }
        return project;
    }
}
