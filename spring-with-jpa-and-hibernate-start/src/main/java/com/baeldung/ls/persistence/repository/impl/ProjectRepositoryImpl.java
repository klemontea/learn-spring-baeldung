package com.baeldung.ls.persistence.repository.impl;

import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.repository.IProjectRepository;

@Repository
public class ProjectRepositoryImpl implements IProjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Project> findById(Long id) {
        var entity = entityManager.find(Project.class, id);
        return Optional.ofNullable(entity);
    }

    @Override
    @Transactional
    public Project save(Project project) {
        entityManager.persist(project);
        return project;
    }

}
