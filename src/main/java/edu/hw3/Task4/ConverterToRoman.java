package edu.hw3.Task4;

import java.util.HashMap;

public class ConverterToRoman {

    private static HashMap<Integer, Character> hashMap;

    public ConverterToRoman() {
        hashMap = new HashMap<>();
        hashMap.put(Numbers.ZERO.getValue(), 'N');
        hashMap.put(Numbers.ONE.getValue(), 'I');
        hashMap.put(Numbers.FIVE.getValue(), 'V');
        hashMap.put(Numbers.TEN.getValue(), 'X');
        hashMap.put(Numbers.FIVE.getValue() * Numbers.TEN.getValue(), 'L');
        hashMap.put(Numbers.HUNDRED.getValue(), 'C');
        hashMap.put(Numbers.FIVE.getValue() * Numbers.HUNDRED.getValue(), 'D');
        hashMap.put(Numbers.THOUSAND.getValue(), 'M');
    }

    public static String convertToRoman(int number) {
        if (number == Numbers.ZERO.getValue()) {
            return "N";
        }
        String result = "";
        while (number >= Numbers.THOUSAND.getValue()) {
            number -= Numbers.THOUSAND.getValue();
            result = result + hashMap.get(Numbers.THOUSAND.getValue());
        }
        for (int i = Numbers.THOUSAND.getValue(); number > Numbers.ZERO.getValue(); i /= 10) {
            if (number >= Numbers.NINE.getValue() * i) {
                number -= Numbers.NINE.getValue() * i;
                result = result + hashMap.get(i) + hashMap.get(Numbers.TEN.getValue() * i);
            }
            if (number >= Numbers.FIVE.getValue() * i) {
                result = result + hashMap.get(Numbers.FIVE.getValue() * i);
                while (number >= Numbers.SIX.getValue() * i) {
                    number -= i;
                    result = result + hashMap.get(i);
                }
                number -= Numbers.FIVE.getValue() * i;
            }
            if (number >= Numbers.FOUR.getValue() * i) {
                number -= (Numbers.FOUR.getValue() * i);
                result = result + hashMap.get(i) + hashMap.get(Numbers.FIVE.getValue() * i);
            }
            while (number >= i) {
                number -= i;
                result = result + hashMap.get(i);
            }
        }
        return result;
    }
}