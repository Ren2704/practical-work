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
@Table(name = "publications")

public class PublicationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publications_id_seq")
    @SequenceGenerator(name = "publications_id_seq", sequenceName = "publications_id_seq", allocationSize = 1, initialValue = 11)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private int year;

    @Column
    private String link;

    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false)
    @JsonBackReference
    private OutstandingPeopleEntity person;
}
