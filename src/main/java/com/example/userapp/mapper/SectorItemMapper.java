package com.example.userapp.mapper;

import com.example.userapp.model.dto.SectorItemDto;
import com.example.userapp.model.entity.SectorItem;
import org.springframework.stereotype.Component;

@Component
public class SectorItemMapper {
    public SectorItemDto fromEntityToDto(SectorItem sectorItem) {

        return SectorItemDto.builder()
                .id(sectorItem.getId())
                .name(sectorItem.getName())
                .isLeaf(sectorItem.isLeaf())
                .parent(sectorItem.getParent())
                .build();
    }
}
