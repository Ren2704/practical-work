package com.example.demo.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Setter
@Getter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"person"})

@Entity
@Table(name = "achievements")
public class AchievementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String title;

    private int year;

    private String description;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person", nullable = false)
    private OutstandingPersonEntity person;
}
