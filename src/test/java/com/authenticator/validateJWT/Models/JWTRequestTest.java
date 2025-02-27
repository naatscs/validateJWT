package com.authenticator.validateJWT.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JWTRequestTest {

    private JWTRequest jwtRequest;

    @BeforeEach
    public void setUp() {
        jwtRequest = new JWTRequest();
    }

    @Test
    public void testGetToken_WhenTokenIsNotSet_ShouldReturnNull() {
        assertNull(jwtRequest.getToken(), "Token should be null when not set");
    }

    @Test
    public void testSetToken_ShouldStoreTokenCorrectly() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiYWRtaW4ifQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        jwtRequest.setToken(token);
        assertEquals(token, jwtRequest.getToken(), "Token should be stored and retrieved correctly");
    }

    @Test
    public void testSetToken_WhenTokenIsNull_ShouldStoreNull() {
        jwtRequest.setToken(null);
        assertNull(jwtRequest.getToken(), "Token should be null when set to null");
    }

    @Test
    public void testSetToken_WhenTokenIsEmpty_ShouldStoreEmptyString() {
        jwtRequest.setToken("");
        assertEquals("", jwtRequest.getToken(), "Token should be an empty string when set to empty");
    }
}
