package com.example.userapp.model.dto;

import com.example.userapp.model.entity.SectorItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SectorItemDto {

    private Long id;
    private String name;
    private boolean isLeaf;
    private SectorItem parent;
}
