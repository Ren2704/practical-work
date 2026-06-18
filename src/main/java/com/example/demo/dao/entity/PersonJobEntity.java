package com.example.demo.dao.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
@Setter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"person", "jobTitle"})

@Entity
@Table(name = "person_job")
public class PersonJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "start_year", nullable = true)
    private Integer startYear;

    @Column(name = "end_year", nullable = true)
    private Integer endYear;

    @Column(name = "is_current")
    private boolean current = false;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person", nullable = false)
    private OutstandingPersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_job_title", nullable = false)
    private JobTitleEntity jobTitle;
}
