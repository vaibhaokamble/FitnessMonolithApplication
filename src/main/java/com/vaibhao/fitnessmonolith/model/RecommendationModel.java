package com.vaibhao.fitnessmonolith.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserModel user;

    // ⚡ Bolt: Optimize performance by explicitly setting fetch type to LAZY
    // JPA defaults @ManyToOne to EAGER, which can cause N+1 query problems.
    // Especially with @JsonIgnore, we want to prevent unnecessary eager loading.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = true)
    @JsonIgnore
    private ActivityModel activity;

    private String type;

    private String recommendations;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")

    private List<String> improvements;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")

    private List<String> suggestions;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")

    private List<String> safety;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
