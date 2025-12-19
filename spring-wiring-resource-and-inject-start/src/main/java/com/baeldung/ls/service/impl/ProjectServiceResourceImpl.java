package com.baeldung.ls.service.impl;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.repository.IProjectRepository;
import com.baeldung.ls.service.IProjectService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceResourceImpl implements IProjectService {

    private IProjectRepository projectRepository;

    private final Logger LOG = LoggerFactory.getLogger(ProjectServiceResourceImpl.class);

    @Override
    public Optional<Project> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Project save(Project project) {
        return null;
    }

    @Resource(name = "projectRepositoryImpl")
    public void setProjectRepository(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        LOG.info("wired projectRepository instance: {}", projectRepository);
    }
}
