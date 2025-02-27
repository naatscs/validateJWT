package com.authenticator.validateJWT.Controller;


import com.authenticator.validateJWT.Models.JWTRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ValidateJWTController {
    @PostMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestBody JWTRequest request) {
        String token = request.getToken();

        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("Token is missing or empty");
        }

        // You can add your JWT validation logic here
        // For now, let's just return the received token
        return ResponseEntity.ok("Received token: " + token);
    }
}
