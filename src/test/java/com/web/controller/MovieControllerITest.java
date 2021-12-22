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
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void givenURIWhenGetAllMoviesThenResponseContainsKeyWords() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("Forrest Gump", contains(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    public void shouldReturnMoviesByGenre() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/genre/{genreId}", 8))
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

    @Test
    void givenURIWhenSortByRatingDescThenResponseOk() throws Exception {
        String sortingType = "desc";
        mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie?rating={rating}", sortingType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void givenURIWhenSortByRatingAscThenResponseOk() throws Exception {
        String sortingType = "asc";
        mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie?rating={rating}", sortingType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void givenURIWhenSortByRatingDescThenJsonNotNull() throws Exception {
        String sortingType = "desc";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie?rating={rating}", sortingType))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
        assertEquals("Матрица 2", contains(content));
    }

    @Test
    void givenURIWhenSortByRatingAscThenJsonNotNull() throws Exception {
        String sortingType = "asc";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie?rating={rating}", sortingType))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
        assertEquals("Матрица 2", contains(content));
    }

    @Test
    void givenURIWhenSortByPriceDescThenResponseOk() throws Exception {
        String sortingType = "desc";
        mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie?price={rating}", sortingType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void givenURIWhenSortByPriceAscThenResponseOk() throws Exception {
        String sortingType = "asc";
        mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie?price={rating}", sortingType))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void givenURIWhenSortByPriceDescThenJsonNotNull() throws Exception {
        String sortingType = "desc";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie?price={rating}", sortingType))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
        assertEquals("Матрица 2", contains(content));
    }

    @Test
    void givenURIWhenSortByPriceAscThenJsonNotNull() throws Exception {
        String sortingType = "asc";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/app/v1/movie?price={rating}", sortingType))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
        assertEquals("Матрица 2", contains(content));
    }

    @Test
    public void shouldReturnMovieById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/{movieId}", 2))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.nameRussian").value("Матрица 2"))
                .andExpect(jsonPath("$.nameNative").value("Matrix 2"))
                .andExpect(jsonPath("$.year").value(2000))
                .andExpect(jsonPath("$.rating").value(10.0))
                .andExpect(jsonPath("$.price").value(100.00))
                .andExpect(jsonPath("$.picturePath")
                        .value("https://upload.wikimedia.org/wikipedia/ru/6/62/Matrix_reloaded.jpg"))
                .andExpect(jsonPath("$.countries.country").value("США"))
                .andExpect(jsonPath("$.genres.genre").value("фантастика"));
    }
}