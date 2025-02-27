package com.authenticator.validateJWT.Utils;

import org.junit.jupiter.api.Test;

    import static org.junit.jupiter.api.Assertions.*;

    public class MathUtilsTest {

        @Test
        public void testIsPrime_WithNegativeNumber_ShouldReturnFalse() {
            assertFalse(MathUtils.isPrime(-5));
        }

        @Test
        public void testIsPrime_WithZero_ShouldReturnFalse() {
            assertFalse(MathUtils.isPrime(0));
        }

        @Test
        public void testIsPrime_WithOne_ShouldReturnFalse() {
            assertFalse(MathUtils.isPrime(1));
        }

        @Test
        public void testIsPrime_WithTwo_ShouldReturnTrue() {
            assertTrue(MathUtils.isPrime(2));
        }

        @Test
        public void testIsPrime_WithThree_ShouldReturnTrue() {
            assertTrue(MathUtils.isPrime(3));
        }

        @Test
        public void testIsPrime_WithFour_ShouldReturnFalse() {
            assertFalse(MathUtils.isPrime(4));
        }

        @Test
        public void testIsPrime_WithLargePrimeNumber_ShouldReturnTrue() {
            assertTrue(MathUtils.isPrime(101));
        }

        @Test
        public void testIsPrime_WithLargeNonPrimeNumber_ShouldReturnFalse() {
            assertFalse(MathUtils.isPrime(100));
        }

        @Test
        public void testIsPrime_WithEdgeCasePrimeNumber_ShouldReturnTrue() {
            assertTrue(MathUtils.isPrime(97));
        }

        @Test
        public void testIsPrime_WithEdgeCaseNonPrimeNumber_ShouldReturnFalse() {
            assertFalse(MathUtils.isPrime(99));
        }
    }


