package com.example.userapp.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserDto {

    private Long id;
    private String name;
    private Set<SectorItemDto> sectorItems;
    private boolean isAgreedToTerms;
}
