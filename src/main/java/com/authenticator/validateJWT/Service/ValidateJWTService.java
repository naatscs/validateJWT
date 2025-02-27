package com.authenticator.validateJWT.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.authenticator.validateJWT.Controller.ValidateJWTController;
import com.authenticator.validateJWT.Utils.MathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class ValidateJWTService {

    private static final Logger logger = LoggerFactory.getLogger(ValidateJWTService.class);

    public boolean validateTokenService(String token){
        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            logger.info("decoded JWT has payload value: {} ", decodedJWT.getPayload());


            return jwtContainNameRoleAndSeedClaims(decodedJWT) && clainNameContainsNumbers(decodedJWT) &&
                    claimRolesHasValidAttributes(decodedJWT) &&
                    claimSeedIsPrime(decodedJWT) &&
                    claimNameMaxRange(decodedJWT);

        }catch (JWTDecodeException e){
            logger.error("Invalid JWT token {} ", e.getMessage());
            return false;
        }
    }

    public static boolean jwtContainNameRoleAndSeedClaims(DecodedJWT decodedJWT){
        Set<String> claimNames = decodedJWT.getClaims().keySet();
        Set<String> expectedClaims = Set.of("Name", "Role", "Seed");

        logger.info("clainNames value is {} ", claimNames);


        return claimNames.equals(expectedClaims);


    }

    public static boolean clainNameContainsNumbers(DecodedJWT decodedJWT){

        String name = decodedJWT.getClaim("Name").asString();

        logger.info("name value is {} ", name);


        //regex para identificar se possui um numero na string
        if (name != null && name.matches(".*\\d.*")) {
            return false;
        }
        return true;

    }

    public static boolean claimRolesHasValidAttributes(DecodedJWT decodedJWT){

        String role = decodedJWT.getClaim("Role").asString();

        logger.info("role value is {} ", role);


        if(role ==null){
            return false;
        }

        String[] roles = role.split(",");

        if (roles.length != 1) {
            return false;
        }

        Set<String> validRoles = Set.of("Admin", "Member", "External");

        return validRoles.contains(role);
    }

    public static boolean claimSeedIsPrime(DecodedJWT decodedJWT){

        String seedValue = decodedJWT.getClaim("Seed").asString();

        logger.info("seed value is {} ", seedValue);

        if (seedValue == null || seedValue.trim().isEmpty()) {
            logger.info("Seed is null");


            return false;
        }

        int seedIntValue;
        try {
            seedIntValue = Integer.parseInt(seedValue);
        } catch (NumberFormatException e) {
            logger.error("Seed is not a valid number: {} ", e.getMessage());

            return false;
        }

        return MathUtils.isPrime(seedIntValue);
    }

    public static boolean claimNameMaxRange(DecodedJWT decodedJWT){

        String nameValue = decodedJWT.getClaim("Name").asString();

        logger.info("nameValue value is {} ", nameValue);


        return nameValue != null && nameValue.length() <= 256;
    }


}

