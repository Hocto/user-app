package com.example.userapp.controller;

import com.example.userapp.model.entity.SectorItem;
import com.example.userapp.model.entity.User;
import com.example.userapp.repository.SectorItemRepository;
import com.example.userapp.repository.UserRepository;
import com.example.userapp.repository.UsersSectorsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.example.userapp.util.SectorItemTestUtil.getSectorItems;
import static com.example.userapp.util.UserTestUtil.getUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RestUserControllerIntegrationTest {

    private static final String GET_USER_URL = "/api/v1/user/{id}";
    private static final String CREATE_USER_URL = "/api/v1/user";
    private static final String UPDATE_USER_URL = "/api/v1/user/{id}";
    private static final String DELETE_USER_URL = "/api/v1/user/{id}";
    private static final String GET_USER_LIST_URL = "/api/v1/users";

    private String availableUserId = "1";
    private String availableSectorItemId = "1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SectorItemRepository sectorItemRepository;

    @Autowired
    private UsersSectorsRepository usersSectorsRepository;

    @Value("classpath:request/valid_create_user_body.json")
    private Resource createUserBody;

    @Value("classpath:request/valid_update_user_body.json")
    private Resource updateUserBody;

    @BeforeEach
    @Transactional
    void beforeEach() {
        List<SectorItem> sectorItems = sectorItemRepository.saveAll(getSectorItems());
        User user = getUser(sectorItems);
        User savedUser = userRepository.save(user);

        availableSectorItemId = String.valueOf(sectorItems.get(sectorItems.size() - 1).getId());
        availableUserId = String.valueOf(savedUser.getId());
    }

    @AfterEach
    @Transactional
    void afterAll() {
        usersSectorsRepository.deleteAll();
        sectorItemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void onValidRequestBodyWithCreateMethodReturnsUserItem() throws Exception {

        MockHttpServletRequestBuilder builder = post(CREATE_USER_URL)
                .content(resourceToString(createUserBody).replace("1", availableSectorItemId))
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.name", is("Tom")))
                .andExpect(jsonPath("$.user.sectorItems", hasSize(1)))
                .andExpect(jsonPath("$.user.sectorItems[0].name", is("Beverages")))
                .andExpect(jsonPath("$.user.sectorItems[0].leaf", is(true)))
                .andExpect(jsonPath("$.user.agreedToTerms", is(true)));
    }

    @Test
    void onValidRequestBodyWithUpdateMethodReturnsUserItem() throws Exception {

        MockHttpServletRequestBuilder builder = put(UPDATE_USER_URL.replace("{id}", availableUserId))
                .content(resourceToString(updateUserBody).replace("1", availableSectorItemId))
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.name", is("Kate")))
                .andExpect(jsonPath("$.user.sectorItems", hasSize(1)))
                .andExpect(jsonPath("$.user.sectorItems[0].name", is("Beverages")))
                .andExpect(jsonPath("$.user.sectorItems[0].leaf", is(true)))
                .andExpect(jsonPath("$.user.agreedToTerms", is(false)));
    }

    @Test
    void onValidRequestBodyWithGetMethodReturnsUserItem() throws Exception {

        MockHttpServletRequestBuilder builder = get(GET_USER_URL.replace("{id}", availableUserId))
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.name", is("David")))
                .andExpect(jsonPath("$.user.sectorItems", hasSize(3)))
                .andExpect(jsonPath("$.user.agreedToTerms", is(true)));
    }

    @Test
    void onGetAllMethodReturnsAllUserItems() throws Exception {

        MockHttpServletRequestBuilder builder = get(GET_USER_LIST_URL)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", hasSize(1)))
                .andExpect(jsonPath("$.users[0].name", is("David")))
                .andExpect(jsonPath("$.users[0].sectorItems", hasSize(3)))
                .andExpect(jsonPath("$.users[0].agreedToTerms", is(true)));
    }

    @Test
    void onDeleteMethodReturnsNothing() throws Exception {

        MockHttpServletRequestBuilder builder = delete(DELETE_USER_URL.replace("{id}", availableUserId))
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(userRepository.count()).isZero();
    }

    private String resourceToString(Resource resource) throws IOException {

        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        return FileCopyUtils.copyToString(reader);
    }
}
