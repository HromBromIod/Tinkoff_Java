package edu.hw10.Task2Test;

import edu.hw10.Task2.CacheProxy;
import edu.hw10.Task2Test.Fibonacci.FibCalculator;
import edu.hw10.Task2Test.Fibonacci.FibCalculatorImp;
import edu.hw10.Task2Test.Palindrome.Palindrome;
import edu.hw10.Task2Test.Palindrome.PalindromeImp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    @Test
    @DisplayName("CacheProxy test with FibCalculator")
    void fibCalculatorTest() {
        FibCalculator fib = new FibCalculatorImp();
        FibCalculator proxyFib = (FibCalculator) CacheProxy.create(fib, fib.getClass());
        long result = proxyFib.fib(5);
        Map<String, Object[]> cache = CacheProxy.getCache();
        assertEquals(5, result);
        assertNotNull(cache);
        assertEquals("[[5]]", Arrays.toString(cache.keySet().toArray()));
        assertEquals("[5, fib]", Arrays.toString(cache.get(Arrays.toString(new Object[] {5}))));
    }

    @Test
    @DisplayName("CacheProxy test with Palindrome")
    void palindromeTest() {
        Palindrome palindrome = new PalindromeImp();
        Palindrome proxyPalindrome = (Palindrome) CacheProxy.create(palindrome, palindrome.getClass());
        boolean result1 = proxyPalindrome.isPalindrome("12321");
        boolean result2 = proxyPalindrome.isPalindrome("12345");
        Map<String, Object[]> cache = CacheProxy.getCache();
        assertTrue(result1);
        assertFalse(result2);
        assertNotNull(cache);
        assertEquals("[[12345], [12321]]", Arrays.toString(cache.keySet().toArray()));
        assertEquals("[true, isPalindrome]", Arrays.toString(cache.get(Arrays.toString(new Object[] {"12321"}))));
    }

    @Test
    @DisplayName("unique values in cache test with FicCalculator")
    void uniqueValuesFibCalculatorTest() {
        FibCalculator fib = new FibCalculatorImp();
        FibCalculator proxyFib = (FibCalculator) CacheProxy.create(fib, fib.getClass());
        proxyFib.fib(5);
        proxyFib.fib(5);
        proxyFib.fib(5);
        proxyFib.fib(5);
        proxyFib.fib(5);
        Map<String, Object[]> cache = CacheProxy.getCache();
        assertNotNull(cache);
        assertEquals(1, cache.size());
    }

    @Test
    @DisplayName("unique values in cache test with Palindrome")
    void uniqueValuesPalindromeTest() {
        FibCalculator fib = new FibCalculatorImp();
        FibCalculator proxyFib = (FibCalculator) CacheProxy.create(fib, fib.getClass());
        proxyFib.fib(5);
        proxyFib.fib(1);
        proxyFib.fib(2);
        proxyFib.fib(3);
        proxyFib.fib(4);
        Map<String, Object[]> cache = CacheProxy.getCache();
        assertNotNull(cache);
        assertEquals(5, cache.size());
    }
}
