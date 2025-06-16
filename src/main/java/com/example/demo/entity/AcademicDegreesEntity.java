package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@EqualsAndHashCode(exclude = {"person"})
@ToString(exclude = {"person"})

@Builder
@Entity
@Table(name = "academic_degrees")

public class AcademicDegreesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "academic_degrees_id_seq")
    @SequenceGenerator(name = "academic_degrees_id_seq", sequenceName = "academic_degrees_id_seq", allocationSize = 1, initialValue = 3)
    private Long id;

    @Column (nullable = false, unique = true)
    private String name;

    @Column (name = "short_name")
    private String shortName;

    @Column
    @Builder.Default
    private Boolean display = true;

    @OneToMany(mappedBy = "academicDegrees", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @Builder.Default
    private Set<OutstandingPeopleEntity> person = Collections.emptySet();
}
