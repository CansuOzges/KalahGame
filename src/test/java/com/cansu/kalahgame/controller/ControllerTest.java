package com.cansu.kalahgame.controller;

import com.cansu.kalahgame.model.Game;
import com.cansu.kalahgame.service.GameService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ControllerTest {


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private GameService service;

    private MockMvc mockMvc;

    @PostConstruct
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test

    public void testNewGame() throws Exception {
        final MockHttpServletRequestBuilder playGameRequest = MockMvcRequestBuilders.post("/games");
        this.mockMvc.perform(playGameRequest).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("STARTED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.size()", Matchers.is(14))).andReturn();
    }

    @Test
    @DirtiesContext
    public void testPlay() throws Exception {
        final Game game = this.service.initGame();
        final MockHttpServletRequestBuilder playGameRequest =
                MockMvcRequestBuilders.put("/games/" + game.getId() + "/pits/1");
        this.mockMvc.perform(playGameRequest).andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("STARTED"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.size()", Matchers.is(14)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits[0].stones").value(0))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits[1].stones").value(7)).andReturn();
    }
}
