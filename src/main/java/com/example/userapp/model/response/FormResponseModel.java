package com.example.userapp.model.response;

import com.example.userapp.model.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FormResponseModel {

    private UserDto user;
}
