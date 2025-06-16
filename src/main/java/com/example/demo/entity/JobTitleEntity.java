package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@EqualsAndHashCode(exclude = {"personJob"})
@ToString(exclude = {"personJob"})

@Builder
@Entity
@Table(name = "job_title")

public class JobTitleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_title_id_seq")
    @SequenceGenerator(name = "job_title_id_seq", sequenceName = "job_title_id_seq", allocationSize = 1, initialValue = 6)
    private Long id;

    @Column (nullable = false, unique = true)
    private String name;

    @Column
    @Builder.Default
    private Boolean display = true;

    @OneToMany(mappedBy = "jobTitle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @Builder.Default
    private Set<PersonJobEntity> personJob = Collections.emptySet();
}
