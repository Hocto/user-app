package com.example.userapp.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "users_sectors")
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@NoArgsConstructor
public class UsersSectors {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long usersId;

    @NotNull
    private Long sectorItemId;
}
