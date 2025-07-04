    package com.example.demo.security;

    import org.springframework.data.jpa.repository.JpaRepository;

    public interface AppUserRepository extends JpaRepository<AppUser, Long> {
        AppUser findByUsername(String username);
        boolean existsByUsername(String username);
    }
