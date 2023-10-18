package com.example.userapp.controller;

import com.example.userapp.repository.SectorItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static com.example.userapp.util.SectorItemTestUtil.getSectorItems;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RestSectorItemControllerIntegrationTest {

    private static final String GET_SECTOR_ITEM_LIST_URL = "/api/v1/sectorItems";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SectorItemRepository sectorItemRepository;

    @BeforeEach
    @Transactional
    void beforeEach() {
        sectorItemRepository.saveAll(getSectorItems());
    }

    @AfterEach
    @Transactional
    void afterAll() {
        sectorItemRepository.deleteAll();
    }

    @Test
    void onValidRequestBodyWithCreateMethodReturnsUserItem() throws Exception {

        MockHttpServletRequestBuilder builder = get(GET_SECTOR_ITEM_LIST_URL)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sectorItems", hasSize(3)));
    }
}
