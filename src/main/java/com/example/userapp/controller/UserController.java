package com.example.userapp.controller;

import com.example.userapp.model.request.FormRequestModel;
import com.example.userapp.model.response.FormResponseListModel;
import com.example.userapp.model.response.FormResponseModel;
import com.example.userapp.repository.SectorItemRepository;
import com.example.userapp.repository.UserRepository;
import com.example.userapp.repository.UsersSectorsRepository;
import com.example.userapp.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@Api(tags = "User API")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<FormResponseListModel> getAllUsers() {

        return new ResponseEntity<>(
                FormResponseListModel.builder().users(userService.retrieveAll()).build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<FormResponseModel> getById(@PathVariable("id") String id) {

        var userDto = userService.retrieveById(Long.valueOf(id));
        return new ResponseEntity<>(
                FormResponseModel.builder().user(userDto).build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/user")
    public ResponseEntity<FormResponseModel> create(@Valid @RequestBody FormRequestModel formRequestModel) {

        var userDto = userService.create(UserService.Parameter.builder()
                .name(formRequestModel.getName())
                .sectorItemIds(formRequestModel.getSectorItemIds())
                .agreedToTerms(formRequestModel.isAgreeToTerms())
                .build());

        return new ResponseEntity<>(FormResponseModel.builder().user(userDto).build(), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<FormResponseModel> update(@Valid @RequestBody FormRequestModel formRequestModel, @PathVariable("id") String userId) {

        var userDto = userService.update(UserService.Parameter.builder()
                .userId(Long.valueOf(userId))
                .name(formRequestModel.getName())
                .sectorItemIds(formRequestModel.getSectorItemIds())
                .agreedToTerms(formRequestModel.isAgreeToTerms())
                .build());

        return new ResponseEntity<>(FormResponseModel.builder().user(userDto).build(), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String userId) {

        userService.delete(UserService.Parameter.builder()
                .userId(Long.valueOf(userId))
                .build());

        return ResponseEntity.ok().build();
    }
}
