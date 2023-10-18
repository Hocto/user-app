package com.example.userapp.controller;

import com.example.userapp.model.dto.SectorItemDto;
import com.example.userapp.model.entity.SectorItem;
import com.example.userapp.model.response.SectorItemListResponseModel;
import com.example.userapp.repository.SectorItemRepository;
import com.example.userapp.repository.UserRepository;
import com.example.userapp.service.SectorItemService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "/api/v1")
@RestController
@RequiredArgsConstructor
@Api(tags = "Secto API")
public class SectorItemController {
    private final SectorItemService sectorItemService;

    @GetMapping("/sectorItems")
    public ResponseEntity<SectorItemListResponseModel> retrieveAll() {

        List<SectorItemDto> sectorItems = sectorItemService.retrieveAll();
        return new ResponseEntity<>(SectorItemListResponseModel.builder().sectorItems(sectorItems).build(), HttpStatus.OK);
    }
}
