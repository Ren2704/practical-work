package com.example.demo.entity;

import com.example.demo.enums.Gender;
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

@EqualsAndHashCode(exclude = {"academicTitlesEntity", "academicDegreesEntity", "educationSubjectEntity", "publications", "achievements", "personJobEntity"})
@ToString(exclude = {"academicTitlesEntity", "academicDegreesEntity", "educationSubjectEntity", "publications", "achievements", "personJobEntity"})

@Builder
@Entity
@Table(name = "outstanding_people")

public class OutstandingPeopleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outstanding_people_id_seq")
    @SequenceGenerator(name = "outstanding_people_id_seq", sequenceName = "outstanding_people_id_seq", allocationSize = 1, initialValue = 21)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String patronymic;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @Column(name = "year_of_birth", nullable = true)
    private int yearOfBirth;

    @Column(name = "year_of_death")
    private Integer yearOfDeath;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @ManyToOne
    @JoinColumn(name = "id_academic_titles")
    @JsonBackReference
    private AcademicTitlesEntity academicTitlesEntity;

    @ManyToOne
    @JoinColumn(name = "id_academic_degrees")
    @JsonBackReference
    private AcademicDegreesEntity academicDegreesEntity;

    @ManyToOne
    @JoinColumn(name = "id_education_subject")
    @JsonBackReference
    private EducationSubjectEntity educationSubjectEntity;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<PublicationsEntity> publications = Collections.emptySet();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<AchievementsEntity> achievements = Collections.emptySet();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<PersonJobEntity> personJobEntity = Collections.emptySet();
}
