package com.example.userapp.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "users")
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_sectors",
            joinColumns = @JoinColumn(name = "usersId"),
            inverseJoinColumns = @JoinColumn(name = "sectorItemId")
    )
    @Size(min = 1, max = 5)
    private List<SectorItem> sectorItems;

    private boolean isAgreedToTerms;
}
