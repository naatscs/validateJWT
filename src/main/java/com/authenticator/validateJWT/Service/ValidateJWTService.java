package com.authenticator.validateJWT.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Set;

public class ValidateJWTService {
    public boolean validateTokenService(String token){
        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            return jwtContainNameRoleAndSeedClaims(decodedJWT) && clainNameContainsNumbers(decodedJWT) ;

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

    public boolean validateJWTClaimRoles(String token){
        return false;
    }

    public boolean claimSeedIsPrime(String token){
        return false;
    }

    public boolean claimNameMaxRange(String token){
        return false;
    }



}

