package com.web.controller;

import com.IntegrationTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals("фантастика", contains(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    public void shouldReturnMoviesByGenre() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/genre"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("драма"))
                .andExpect(jsonPath("$.name").value("криминал"))
                .andExpect(jsonPath("$.name").value("фэнтези"))
                .andExpect(jsonPath("$.name").value("детектив"))
                .andExpect(jsonPath("$.name").value("мелодрама"))
                .andExpect(jsonPath("$.name").value("биография"))
                .andExpect(jsonPath("$.name").value("комедия"))
                .andExpect(jsonPath("$.name").value("фантастика"))
                .andExpect(jsonPath("$.name").value("боевик"))
                .andExpect(jsonPath("$.name").value("триллер"))
                .andExpect(jsonPath("$.name").value("приключения"))
                .andExpect(jsonPath("$.name").value("аниме"))
                .andExpect(jsonPath("$.name").value("мультфильм"))
                .andExpect(jsonPath("$.name").value("семейный"))
                .andExpect(jsonPath("$.name").value("вестерн"));
    }
}