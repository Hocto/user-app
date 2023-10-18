package com.example.userapp.service;

import com.example.userapp.exception.EntityNotFoundException;
import com.example.userapp.mapper.UserMapper;
import com.example.userapp.model.dto.UserDto;
import com.example.userapp.model.entity.SectorItem;
import com.example.userapp.model.entity.User;
import com.example.userapp.repository.SectorItemRepository;
import com.example.userapp.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final SectorItemRepository sectorItemRepository;

    private final UserMapper userMapper;

    public UserDto retrieveById(Long id) {

        var user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Id %s does not exist", id),
                User.class.getSimpleName()
        ));

        return userMapper.fromEntityToDto(user);
    }

    public List<UserDto> retrieveAll() {

        return userRepository.findAll().stream().map(userMapper::fromEntityToDto).collect(Collectors.toList());
    }

    public UserDto create(Parameter parameter) {

        List<SectorItem> sectorItems = sectorItemRepository.findAllByIds(parameter.sectorItemIds());
        var savedUser = userRepository.save(getUser(parameter.name(), sectorItems, parameter.agreedToTerms()));

        return userMapper.fromEntityToDto(savedUser);
    }

    public UserDto update(Parameter parameter) {

        User user = userRepository.findById(parameter.userId()).orElseThrow(() -> new EntityNotFoundException(
                String.format("Id %s does not exist", parameter.userId()),
                User.class.getSimpleName()
        ));

        List<SectorItem> sectorItems = sectorItemRepository.findAllByIds(parameter.sectorItemIds());
        user.setName(parameter.name());
        user.setSectorItems(sectorItems);
        user.setAgreedToTerms(parameter.agreedToTerms());

        var updatedUser = userRepository.save(user);

        return userMapper.fromEntityToDto(updatedUser);
    }

    public void delete(Parameter parameter) {

        userRepository.deleteById(parameter.userId());
    }

    private User getUser(String name, List<SectorItem> sectorItems, boolean agreedToTerms) {
        var user = new User();

        user.setName(name);
        user.setSectorItems(sectorItems);
        user.setAgreedToTerms(agreedToTerms);

        return user;

    }

    @Builder
    public record Parameter(Long userId, String name, Set<Long> sectorItemIds, boolean agreedToTerms) {
    }
}
