package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@EqualsAndHashCode(exclude = {"person"})
@ToString(exclude = {"person"})

@Builder
@Entity
@Table(name = "achievements")

public class AchievementsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "achievements_id_seq")
    @SequenceGenerator(name = "achievements_id_seq", sequenceName = "achievements_id_seq", allocationSize = 1, initialValue = 16)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private int year;

    @Column
    private String description;

    @Column
    @Builder.Default
    private Boolean display = true;

    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false)
    @JsonBackReference
    private OutstandingPeopleEntity person;
}
