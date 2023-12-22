package edu.hw10.Task2Test.Palindrome;

import edu.hw10.Task2.Cache;

public interface Palindrome {
    @Cache(persist = true)
    public boolean isPalindrome(String s);
}
