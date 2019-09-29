package br.com.alura.forum.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TopicController.class)
class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnTopics() throws Exception {
        var uri = "/api/topics";
        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].shortDescription").value("Problemas com o JSF"))
                .andExpect(jsonPath( "$.[0].secondsSinceLastUpdate").value(0))
                .andExpect(jsonPath( "$.[0].ownerName").value("Fulano"))
                .andExpect(jsonPath( "$.[0].courseName").value("Java e JSF"))
                .andExpect(jsonPath( "$.[0].subcategoryName").value("Java"))
                .andExpect(jsonPath( "$.[0].categoryName").value("Programação"))
                .andExpect(jsonPath( "$.[0].numberOfResponses").value(0));
    }
}
