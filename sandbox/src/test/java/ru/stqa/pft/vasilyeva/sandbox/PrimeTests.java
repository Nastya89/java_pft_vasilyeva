package ru.stqa.pft.vasilyeva.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
    @Test
    public void testPrimes1(){
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void testPrimes(){
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
    }
}
