package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.util.Collections;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@EqualsAndHashCode(exclude = {"person"})
@ToString(exclude = {"person"})

@Builder
@Entity
@Table(name = "education_subject")

public class EducationSubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "education_subject_id_seq")
    @SequenceGenerator(name = "education_subject_id_seq", sequenceName = "education_subject_id_seq", allocationSize = 1, initialValue = 13)
    private Long id;

    @Column (nullable = false, unique = true)
    private String name;

    @Column (name = "short_name")
    private String shortName;

    @Column
    @Builder.Default
    private Boolean display = true;

    @OneToMany(mappedBy = "educationSubject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @Builder.Default
    private Set<OutstandingPeopleEntity> person = Collections.emptySet();
}
