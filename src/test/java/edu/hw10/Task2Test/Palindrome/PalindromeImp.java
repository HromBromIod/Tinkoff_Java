package edu.hw10.Task2Test.Palindrome;

public class PalindromeImp implements Palindrome {

    @Override
    public boolean isPalindrome(String str) {
        int length = str.length();
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(i) != str.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
