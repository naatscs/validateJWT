package com.authenticator.validateJWT.Service;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateJWTServiceTest {

    @Test
    void testJwtContainNameRoleAndSeedClaims_ValidClaims() {
        DecodedJWT decodedJWT = mockDecodedJWT(Map.of(
                "Name", mockClaim("TestName"),
                "Role", mockClaim("Admin"),
                "Seed", mockClaim("12345")
        ));

        assertTrue(ValidateJWTService.jwtContainNameRoleAndSeedClaims(decodedJWT));
    }

    @Test
    void testJwtContainNameRoleAndSeedClaims_MissingClaim() {
        DecodedJWT decodedJWT = mockDecodedJWT(Map.of(
                "Name", mockClaim("TestName"),
                "Role", mockClaim("Admin")
        ));

        assertFalse(ValidateJWTService.jwtContainNameRoleAndSeedClaims(decodedJWT));
    }

    @Test
    void testJwtContainNameRoleAndSeedClaims_ExtraClaim() {
        DecodedJWT decodedJWT = mockDecodedJWT(Map.of(
                "Name", mockClaim("TestName"),
                "Role", mockClaim("Admin"),
                "Seed", mockClaim("12345"),
                "ExtraClaim", mockClaim("ExtraValue")
        ));

        assertFalse(ValidateJWTService.jwtContainNameRoleAndSeedClaims(decodedJWT));
    }

    @Test
    void testClaimNameContainsNumbers_ValidName() {
        DecodedJWT decodedJWT = mockDecodedJWTName("TestName");

        assertTrue(ValidateJWTService.clainNameContainsNumbers(decodedJWT)); // No numbers in name
    }

    @Test
    void testClaimNameContainsNumbers_NameWithNumbers() {
        DecodedJWT decodedJWT = mockDecodedJWTName("Test123");

        assertFalse(ValidateJWTService.clainNameContainsNumbers(decodedJWT)); // Name contains numbers
    }


    private DecodedJWT mockDecodedJWTName(String name) {
        DecodedJWT decodedJWT = org.mockito.Mockito.mock(DecodedJWT.class);
        Claim claim = org.mockito.Mockito.mock(Claim.class);
        org.mockito.Mockito.when(claim.asString()).thenReturn(name);
        org.mockito.Mockito.when(decodedJWT.getClaim("Name")).thenReturn(claim);
        return decodedJWT;
    }

    private DecodedJWT mockDecodedJWT(Map<String, Claim> claims) {
        DecodedJWT decodedJWT = org.mockito.Mockito.mock(DecodedJWT.class);
        org.mockito.Mockito.when(decodedJWT.getClaims()).thenReturn(claims);
        return decodedJWT;
    }

    private Claim mockClaim(String value) {
        Claim claim = org.mockito.Mockito.mock(Claim.class);
        org.mockito.Mockito.when(claim.asString()).thenReturn(value);
        return claim;
    }


    @Test
    void testClaimRolesHasValidAttributes_ValidRole() {
        DecodedJWT decodedJWT = mockDecodedJWTRole("Admin");

        assertTrue(ValidateJWTService.claimRolesHasValidAttributes(decodedJWT)); // Valid role "Admin"
    }

    @Test
    void testClaimRolesHasValidAttributes_InvalidRole() {
        DecodedJWT decodedJWT = mockDecodedJWTRole("InvalidRole");

        assertFalse(ValidateJWTService.claimRolesHasValidAttributes(decodedJWT)); // Invalid role
    }

    @Test
    void testClaimRolesHasValidAttributes_MultipleRoles() {
        DecodedJWT decodedJWT = mockDecodedJWTRole("Admin,Member");

        assertFalse(ValidateJWTService.claimRolesHasValidAttributes(decodedJWT)); // Multiple roles, should return false
    }

    @Test
    void testClaimRolesHasValidAttributes_NullRole() {
        DecodedJWT decodedJWT = mockDecodedJWTRole(null);

        assertFalse(ValidateJWTService.claimRolesHasValidAttributes(decodedJWT)); // Null role, should return false
    }

    private DecodedJWT mockDecodedJWTRole(String role) {
        DecodedJWT decodedJWT = org.mockito.Mockito.mock(DecodedJWT.class);
        Claim claim = org.mockito.Mockito.mock(Claim.class);
        org.mockito.Mockito.when(claim.asString()).thenReturn(role);
        org.mockito.Mockito.when(decodedJWT.getClaim("Role")).thenReturn(claim);
        return decodedJWT;
    }

    @Test
    void testClaimSeedIsPrime_ValidPrimeNumber() {
        DecodedJWT decodedJWT = mockDecodedJWTSeedValue("7");

        assertTrue(ValidateJWTService.claimSeedIsPrime(decodedJWT)); // 7 is prime
    }

    @Test
    void testClaimSeedIsPrime_ValidNonPrimeNumber() {
        DecodedJWT decodedJWT = mockDecodedJWTSeedValue("8");

        assertFalse(ValidateJWTService.claimSeedIsPrime(decodedJWT)); // 8 is not prime
    }

    @Test
    void testClaimSeedIsPrime_NullSeed() {
        DecodedJWT decodedJWT = mockDecodedJWTSeedValue(null);

        assertFalse(ValidateJWTService.claimSeedIsPrime(decodedJWT)); // Null seed value
    }

    @Test
    void testClaimSeedIsPrime_EmptySeed() {
        DecodedJWT decodedJWT = mockDecodedJWTSeedValue("");

        assertFalse(ValidateJWTService.claimSeedIsPrime(decodedJWT)); // Empty seed value
    }

    @Test
    void testClaimSeedIsPrime_InvalidSeed() {
        DecodedJWT decodedJWT = mockDecodedJWTSeedValue("4");

        assertFalse(ValidateJWTService.claimSeedIsPrime(decodedJWT)); // Invalid seed, not a number
    }

    private DecodedJWT mockDecodedJWTSeedValue(String seedValue) {
        DecodedJWT decodedJWT = org.mockito.Mockito.mock(DecodedJWT.class);
        Claim claim = org.mockito.Mockito.mock(Claim.class);
        org.mockito.Mockito.when(claim.asString()).thenReturn(seedValue);
        org.mockito.Mockito.when(decodedJWT.getClaim("Seed")).thenReturn(claim);
        return decodedJWT;
    }

    @Test
    void testClaimNameMaxRange_ValidName() {
        DecodedJWT decodedJWT = mockDecodedJWTName("John Doe");

        assertTrue(ValidateJWTService.claimNameMaxRange(decodedJWT)); // Name length <= 256
    }

    @Test
    void testClaimNameMaxRange_NameExceedsMaxLength() {
        String longName = "A".repeat(257); // Name with 257 characters
        DecodedJWT decodedJWT = mockDecodedJWTName(longName);

        assertFalse(ValidateJWTService.claimNameMaxRange(decodedJWT)); // Name length > 256
    }

    @Test
    void testClaimNameMaxRange_NullName() {
        DecodedJWT decodedJWT = mockDecodedJWTName(null);

        assertFalse(ValidateJWTService.claimNameMaxRange(decodedJWT)); // Null name is valid
    }


}

