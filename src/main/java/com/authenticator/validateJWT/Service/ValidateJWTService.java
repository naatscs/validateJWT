package com.authenticator.validateJWT.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class ValidateJWTService {
    public boolean validateTokenService(String token){
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
        }catch (JWTDecodeException e){
            System.err.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean jwtContainClaims(String token){
        return false;
    }

    public boolean clainNameContainsNumbers(String token){
        return false;
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

