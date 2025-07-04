package com.example.demo.module2;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "newmodule")
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Newmodule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "is_active")
    private boolean isActive;
    
    @Column(name = "is_featured")
    private boolean isFeatured;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "price", precision = 10, scale = 2)
    private Double price;
    
    @Version
    private Long version;
    
    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadata;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Custom getter for Optional fields
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<Double> getPrice() {
        return Optional.ofNullable(price);
    }

    public Optional<String> getMetadata() {
        return Optional.ofNullable(metadata);
    }
}