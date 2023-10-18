package com.example.userapp.model.response;

import com.example.userapp.model.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FormResponseListModel {

    private List<UserDto> users;
}
