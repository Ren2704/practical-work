package com.example.demo.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"personJob"})

@Builder
@Entity
@Table(name = "job_title")

public class JobTitleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column (nullable = false, unique = true)
    private String name;

    @Builder.Default
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "jobTitle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @Builder.Default
    private Set<PersonJobEntity> personJob = Collections.emptySet();
}
