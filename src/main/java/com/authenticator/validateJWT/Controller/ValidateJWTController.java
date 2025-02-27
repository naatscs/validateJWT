package com.authenticator.validateJWT.Controller;


import com.authenticator.validateJWT.Models.JWTRequest;
import com.authenticator.validateJWT.Service.ValidateJWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ValidateJWTController {

    private static final Logger logger = LoggerFactory.getLogger(ValidateJWTController.class);

    @PostMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestBody JWTRequest request) {
        String token = request.getToken();

        logger.info("token has value: {}", token);



        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("Token is missing or empty");
        }

        ValidateJWTService validateJWTService = new ValidateJWTService();

        Boolean isValid = validateJWTService.validateTokenService(token);

        logger.info("is valid is: {}", isValid);


        String response = "";

        if(isValid == true){
            response = "verdadeiro";
        }else{
            response = "falso";
        }



        return ResponseEntity.ok(response);
    }
}
