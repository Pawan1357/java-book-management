package com.exam.library_management.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoleBasedAccessIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String registerAndLogin(String name, String email, String password) throws Exception {

        String registerRequest = String.format("""
                {
                  "name": "%s",
                  "email": "%s",
                  "password": "%s"
                }
                """, name, email, password);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registerRequest));

        String loginRequest = String.format("""
                {
                  "email": "%s",
                  "password": "%s"
                }
                """, email, password);

        String response = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequest))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode node = objectMapper.readTree(response);
        String token = node.get("token").asText();
        assertNotNull(token);
        return token;
    }

    @Test
    void shouldAllowAdminToDeleteBook() throws Exception {

        String adminToken = registerAndLogin("Admin User", "admin@test.com", "Password123");

        String bookRequest = """
                {
                  "title": "Admin Book",
                  "author": "Author",
                  "isbn": "1111111111"
                }
                """;

        mockMvc.perform(post("/api/books")
                .header("Authorization", "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookRequest))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/api/books/1")
                .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDenyUserDeletingBookIfNotAdmin() throws Exception {

        String userToken = registerAndLogin("Normal User", "user@test.com", "Password123");

        String bookRequest = """
                {
                  "title": "User Book",
                  "author": "Author",
                  "isbn": "2222222222"
                }
                """;

        mockMvc.perform(post("/api/books")
                .header("Authorization", "Bearer " + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookRequest))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/api/books/1")
                .header("Authorization", "Bearer " + userToken))
                .andExpect(status().isForbidden());
    }
}
