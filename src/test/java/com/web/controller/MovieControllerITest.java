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


class MovieControllerITest extends IntegrationTestBase {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenWebAppContextThenItProvidesMovieController() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("movieController"));
    }

    @Test
    public void givenURIWhenMockMVCThenReturnsStatusIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenURIWhenMockMVCThenReturnsContentTypeJson() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("application/json;charset=UTF-8",
                mvcResult.getResponse().getContentType());
    }

    @Test
    public void givenURIWhenMockMVCThenResponseContainsKeyWords() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("Forrest Gump", contains(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    public void shouldReturnMoviesByGenre() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/genre/{genreId}", "фантастика"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.nameRussian").value("Матрица"))
                .andExpect(jsonPath("$.nameNative").value("Matrix"))
                .andExpect(jsonPath("$.year").value(1999))
                .andExpect(jsonPath("$.rating").value(10.0))
                .andExpect(jsonPath("$.price").value(100.00))
                .andExpect(jsonPath("$.picturePath")
                        .value("https://upload.wikimedia.org/wikipedia/ru/thumb/9/9d/Matrix-DVD.jpg/217px-Matrix-DVD.jpg"));
    }

}