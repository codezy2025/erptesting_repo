package com.example.demo.module2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.java.coreTemplate.model.dto.Newmodule;

import java.util.List;
import java.util.Optional;

public interface NewmoduleRepository extends JpaRepository<Newmodule, Long> {

    // Find by exact name match
    Optional<Newmodule> findByName(String name);

    // Find by name containing (case-insensitive)
    List<Newmodule> findByNameContainingIgnoreCase(String name);

    // Find by active status
    List<Newmodule> findByActiveTrue();

    // Custom query with JPQL
    @Query("SELECT n FROM Newmodule n WHERE n.createdAt > CURRENT_DATE - :days")
    List<Newmodule> findRecentModules(@Param("days") int days);

    // Native query example
    @Query(value = "SELECT * FROM new_modules WHERE description LIKE %:keyword%", nativeQuery = true)
    List<Newmodule> searchByDescription(@Param("keyword") String keyword);

    // Projection query
    @Query("SELECT n.name FROM Newmodule n WHERE n.id = :id")
    Optional<String> findModuleNameById(@Param("id") Long id);

    // Update query
    @Modifying
    @Query("UPDATE Newmodule n SET n.active = :active WHERE n.id = :id")
    int updateActiveStatus(@Param("id") Long id, @Param("active") boolean active);

    // Find using multiple criteria
    List<Newmodule> findByNameAndActive(String name, boolean active);

    // Find with sorting
    List<Newmodule> findByOrderByNameAsc();

    // Find with pagination (used with Pageable parameter)
    Page<Newmodule> findByActive(boolean active, Pageable pageable);
}