package com.example.demo.dao.entity;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"person", "jobTitle"})
@Builder
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
    @Builder.Default
    private boolean current = false;

    @Builder.Default
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false)
    @JsonBackReference
    private OutstandingPersonEntity person;

    @ManyToOne
    @JoinColumn(name = "id_job_title", nullable = false)
    @JsonBackReference
    private JobTitleEntity jobTitle;
}
