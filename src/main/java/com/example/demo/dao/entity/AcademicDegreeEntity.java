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
@ToString(exclude = {"person"})

@Entity
@Table(name = "academic_degrees")
public class AcademicDegreeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @OneToMany(mappedBy = "academicDegree", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OutstandingPersonEntity> person = new HashSet<>();
}
