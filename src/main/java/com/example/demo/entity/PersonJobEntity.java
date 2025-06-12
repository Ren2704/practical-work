package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
@EqualsAndHashCode(exclude = {"person", "jobTitleEntity"})
@ToString(exclude = {"person", "jobTitleEntity"})
@Builder
@Entity
@Table(name = "person_job")
public class PersonJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_jobe_id_seq")
    @SequenceGenerator(name = "person_job_id_seq", sequenceName = "person_job_id_seq", allocationSize = 1, initialValue = 13)
    private Long id;
    @Column(name = "start_year", nullable = true)
    private Integer startYear;
    @Column(name = "end_year", nullable = true)
    private Integer endYear;
    @Column(name = "is_current")
    private boolean isCurrent = false;
    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false)
    @JsonBackReference
    private OutstandingPeopleEntity person;
    @ManyToOne
    @JoinColumn(name = "id_job_title", nullable = false)
    @JsonBackReference
    private JobTitleEntity jobTitleEntity;
}
