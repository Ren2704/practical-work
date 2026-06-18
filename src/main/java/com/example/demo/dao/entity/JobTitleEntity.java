package com.example.demo.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Setter
@Getter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"personJob"})

@Entity
@Table(name = "job_title")
public class JobTitleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column (nullable = false, unique = true)
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @OneToMany(mappedBy = "jobTitle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PersonJobEntity> personJob = new HashSet<>();
}
