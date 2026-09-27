package com.bugtracker.controller;

import com.bugtracker.dto.BugRequestDTO;
import com.bugtracker.dto.RegisterRequestDTO;
import com.bugtracker.enums.Priority;
import com.bugtracker.enums.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.jayway.jsonpath.JsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BugControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String userToken;
    private String adminToken;

    @BeforeEach
    public void setup() throws Exception {
        // Create USER
        RegisterRequestDTO userReq = new RegisterRequestDTO("user@example.com", "password", "USER");
        MvcResult userRes = mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userReq)))
                .andReturn();
        if(userRes.getResponse().getStatus() == 200) {
            userToken = JsonPath.read(userRes.getResponse().getContentAsString(), "$.token");
        } else {
             // Already exists, just login
             MvcResult loginRes = mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"user@example.com\",\"password\":\"password\"}")).andReturn();
             userToken = JsonPath.read(loginRes.getResponse().getContentAsString(), "$.token");
        }

        // Create ADMIN
        RegisterRequestDTO adminReq = new RegisterRequestDTO("admin@example.com", "password", "ADMIN");
        MvcResult adminRes = mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(adminReq)))
                .andReturn();
        if(adminRes.getResponse().getStatus() == 200) {
            adminToken = JsonPath.read(adminRes.getResponse().getContentAsString(), "$.token");
        } else {
             MvcResult loginRes = mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"admin@example.com\",\"password\":\"password\"}")).andReturn();
             adminToken = JsonPath.read(loginRes.getResponse().getContentAsString(), "$.token");
        }
    }

    @Test
    public void testCreateBugWithoutToken_Unauthorized() throws Exception {
        BugRequestDTO req = new BugRequestDTO("Test Bug", "Test Description", Priority.HIGH, Status.OPEN);
        mockMvc.perform(post("/bugs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testCreateBugWithToken_Success() throws Exception {
        BugRequestDTO req = new BugRequestDTO("Test Bug", "Test Description", Priority.HIGH, Status.OPEN);
        mockMvc.perform(post("/bugs")
                .header("Authorization", "Bearer " + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testDeleteBugWithUserToken_Forbidden() throws Exception {
        BugRequestDTO req = new BugRequestDTO("Delete Bug", "Delete Description", Priority.LOW, Status.OPEN);
        MvcResult res = mockMvc.perform(post("/bugs")
                .header("Authorization", "Bearer " + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andReturn();
        Integer id = JsonPath.read(res.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(delete("/bugs/" + id)
                .header("Authorization", "Bearer " + userToken))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testDeleteBugWithAdminToken_Success() throws Exception {
        BugRequestDTO req = new BugRequestDTO("Delete Bug 2", "Delete Description", Priority.LOW, Status.OPEN);
        MvcResult res = mockMvc.perform(post("/bugs")
                .header("Authorization", "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andReturn();
        Integer id = JsonPath.read(res.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(delete("/bugs/" + id)
                .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk());
    }
}
