package com.example.userapp.mapper;

import com.example.userapp.model.dto.UserDto;
import com.example.userapp.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final SectorItemMapper sectorItemMapper;

    public UserDto fromEntityToDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .sectorItems(user.getSectorItems().stream()
                        .map(sectorItemMapper::fromEntityToDto).collect(Collectors.toSet()))
                .isAgreedToTerms(user.isAgreedToTerms())
                .build();

    }
}
