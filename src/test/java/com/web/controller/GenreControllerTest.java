package com.web.controller;

import com.IntegrationTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GenreControllerTest extends IntegrationTestBase {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenURIWhenMockMVCThenReturnsStatusIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/genre")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenURIWhenMockMVCThenReturnsContentTypeJson() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/v1/genre")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("application/json;charset=UTF-8",
                mvcResult.getResponse().getContentType());
    }

    @Test
    public void givenURIWhenMockMVCThenResponseContainsKeyWords() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/v1/genre")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("????????????????????", contains(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    public void shouldReturnMoviesByGenre() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/genre"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("??????????"))
                .andExpect(jsonPath("$.name").value("????????????????"))
                .andExpect(jsonPath("$.name").value("??????????????"))
                .andExpect(jsonPath("$.name").value("????????????????"))
                .andExpect(jsonPath("$.name").value("??????????????????"))
                .andExpect(jsonPath("$.name").value("??????????????????"))
                .andExpect(jsonPath("$.name").value("??????????????"))
                .andExpect(jsonPath("$.name").value("????????????????????"))
                .andExpect(jsonPath("$.name").value("????????????"))
                .andExpect(jsonPath("$.name").value("??????????????"))
                .andExpect(jsonPath("$.name").value("??????????????????????"))
                .andExpect(jsonPath("$.name").value("??????????"))
                .andExpect(jsonPath("$.name").value("????????????????????"))
                .andExpect(jsonPath("$.name").value("????????????????"))
                .andExpect(jsonPath("$.name").value("??????????????"));
    }
}