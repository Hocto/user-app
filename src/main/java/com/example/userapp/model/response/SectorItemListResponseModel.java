package com.example.userapp.model.response;

import com.example.userapp.model.dto.SectorItemDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class SectorItemListResponseModel {

    private List<SectorItemDto> sectorItems;
}
