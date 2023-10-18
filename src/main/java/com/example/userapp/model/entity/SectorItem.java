package com.example.userapp.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "sector_item")
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@NoArgsConstructor
public class SectorItem {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private boolean isLeaf;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private SectorItem parent;
}
