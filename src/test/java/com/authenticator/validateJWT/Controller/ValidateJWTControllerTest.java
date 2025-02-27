package com.authenticator.validateJWT.Controller;

import com.authenticator.validateJWT.Configurations.SecurityConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ValidateJWTController.class)
@Import(SecurityConfigTest.class)
public class ValidateJWTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testValidateToken_ValidToken() throws Exception {
        String requestBody = """
                {
                  "token": "eyJhbGciOiJIUzI1NiJ9..."
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/validate-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Received token: eyJhbGciOiJIUzI1NiJ9..."));
    }

    @Test
    public void testValidateToken_EmptyToken() throws Exception {
        String requestBody = """
                {
                  "token": ""
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/validate-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Token is missing or empty"));
    }

    @Test
    public void testValidateToken_NoToken() throws Exception {
        String requestBody = "{}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/validate-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Token is missing or empty"));
    }
}
