package com.example.userapp.service;

import com.example.userapp.mapper.SectorItemMapper;
import com.example.userapp.model.dto.SectorItemDto;
import com.example.userapp.repository.SectorItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectorItemService {

    private final SectorItemRepository sectorItemRepository;
    private final SectorItemMapper sectorItemMapper;

    public List<SectorItemDto> retrieveAll() {

        return sectorItemRepository.findAll().stream().map(sectorItemMapper::fromEntityToDto).collect(Collectors.toList());
    }
}
