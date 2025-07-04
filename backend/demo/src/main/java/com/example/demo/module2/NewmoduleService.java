package com.example.demo.module2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.java.coreTemplate.repository.NewmoduleRepository;
import com.java.coreTemplate.model.dto.Newmodule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewmoduleService {

    private final NewmoduleRepository repository;

    @Transactional
    public Newmodule save(Newmodule entity) {
        log.info("Saving new module: {}", entity);
        return repository.save(entity);
    }

    public Optional<Newmodule> findById(Long id) {
        log.debug("Fetching module by id: {}", id);
        return repository.findById(id);
    }

    public List<Newmodule> findAll() {
        log.debug("Fetching all modules");
        return repository.findAll();
    }

    public Page<Newmodule> findAll(Pageable pageable) {
        log.debug("Fetching all modules with pagination");
        return repository.findAll(pageable);
    }

    public List<Newmodule> findAllActive() {
        log.debug("Fetching all active modules");
        return repository.findByIsActiveTrue();
    }

    @Transactional
    public Newmodule update(Newmodule entity) {
        log.info("Updating module with id: {}", entity.getId());
        return repository.save(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting module with id: {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public void deactivate(Long id) {
        log.info("Deactivating module with id: {}", id);
        repository.findById(id).ifPresent(module -> {
            module.setActive(false);
            repository.save(module);
        });
    }

    public boolean existsById(Long id) {
        log.debug("Checking if module exists with id: {}", id);
        return repository.existsById(id);
    }

    public List<Newmodule> searchByName(String name) {
        log.debug("Searching modules by name: {}", name);
        return repository.findByNameContainingIgnoreCase(name);
    }
}