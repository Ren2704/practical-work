package com.example.demo.dao.entity;

import com.example.demo.dao.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "outstanding_people")

public class OutstandingPersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(nullable = false)
    @ToString.Include
    private String name;

    @Column(nullable = false)
    @ToString.Include
    private String surname;

    @Column(nullable = false)
    @ToString.Include
    private String patronymic;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @ToString.Include
    private Gender gender = Gender.NOT_SELECTED;

    @Column(name = "year_of_birth")
    @ToString.Include
    private int yearOfBirth;

    @Column(name = "year_of_death")
    @ToString.Include
    private Integer yearOfDeath;

    @Column(name = "photo_url")
    @ToString.Include
    private String photoUrl;

    @Column(columnDefinition = "TEXT")
    @ToString.Include
    private String biography;

    @Builder.Default
    @ToString.Include
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "id_academic_titles")
    @JsonBackReference
    private AcademicTitleEntity academicTitles;

    @ManyToOne
    @JoinColumn(name = "id_academic_degrees")
    @JsonBackReference
    private AcademicDegreeEntity academicDegrees;

    @ManyToOne
    @JoinColumn(name = "id_education_subject")
    @JsonBackReference
    private EducationSubjectEntity educationSubject;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @Builder.Default
    private Set<AchievementEntity> achievements = Collections.emptySet();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @Builder.Default
    private Set<PersonJobEntity> personJob = Collections.emptySet();
}
