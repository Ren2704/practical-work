package com.example.demo.dao.entity;

import com.example.demo.dao.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)

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
    @ToString.Include
    private Gender gender = Gender.NOT_SELECTED;

    @Column(name = "year_of_birth")
    @ToString.Include
    private int yearOfBirth;

    @Column(name = "year_of_death")
    @ToString.Include
    private Integer yearOfDeath;

    @Column(name = "photo_link")
    @ToString.Include
    private String photoLink;

    @Column(name = "content_type")
    private String contentType;

    @Column(columnDefinition = "TEXT")
    @ToString.Include
    private String biography;

    @ToString.Include
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_academic_title")
    private AcademicTitleEntity academicTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_academic_degree")
    private AcademicDegreeEntity academicDegree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_education_subject")
    private EducationSubjectEntity educationSubject;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AchievementEntity> achievements = new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PersonJobEntity> personJob = new HashSet<>();
}
