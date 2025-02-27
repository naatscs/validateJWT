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
    public void testValidateToken_validToken() throws Exception {
        String requestBody = """
                {
                  "token": "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/validate-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testValidateToken_inValidToken() throws Exception {
        String requestBody = """
                {
                  "token": "eyJhbGciOiJIUzI1NiJ9..."
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/validate-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
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
