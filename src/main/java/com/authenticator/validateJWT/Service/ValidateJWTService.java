package com.authenticator.validateJWT.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.authenticator.validateJWT.Utils.MathUtils;

import java.util.Set;

public class ValidateJWTService {
    public boolean validateTokenService(String token){
        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            return jwtContainNameRoleAndSeedClaims(decodedJWT) && clainNameContainsNumbers(decodedJWT) &&
                    claimRolesHasValidAttributes(decodedJWT) &&
                    claimSeedIsPrime(decodedJWT) &&
                    claimNameMaxRange(decodedJWT);

        }catch (JWTDecodeException e){
            System.err.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
    }

    public boolean jwtContainNameRoleAndSeedClaims(DecodedJWT decodedJWT){
        Set<String> claimNames = decodedJWT.getClaims().keySet();
        Set<String> expectedClaims = Set.of("Name", "Role", "Seed");

        return claimNames.equals(expectedClaims);


    }

    public boolean clainNameContainsNumbers(DecodedJWT decodedJWT){
        String name = decodedJWT.getClaim("Name").asString();
            //regex para identificar se possui um numero na string
        if (name != null && name.matches(".*\\d.*")) {
            return false;
        }
        return true;

    }

    public boolean claimRolesHasValidAttributes(DecodedJWT decodedJWT){

        String role = decodedJWT.getClaim("Role").asString();

        String[] roles = role.split(",");

        if (roles.length != 1) {
            return false;
        }

        Set<String> validRoles = Set.of("Admin", "Member", "External");

        return validRoles.contains(role);
    }

    public boolean claimSeedIsPrime(DecodedJWT decodedJWT){

        String seedValue = decodedJWT.getClaim("Seed").asString();

        if (seedValue == null || seedValue.trim().isEmpty()) {
            return false;
        }

        int seedIntValue;
        try {
            seedIntValue = Integer.parseInt(seedValue);
        } catch (NumberFormatException e) {
            System.err.println("Seed is not a valid number: " + e.getMessage());
            return false;
        }

        return MathUtils.isPrime(seedIntValue);
    }

    public boolean claimNameMaxRange(DecodedJWT decodedJWT){

        String nameValue = decodedJWT.getClaim("Name").asString();

        return nameValue != null && nameValue.length() <= 256;
    }


}

