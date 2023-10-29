package edu.hw3;

import edu.hw3.Task4.ConverterToRoman;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    private Task4Test() {
    }

    @DisplayName("Some tests")
    @ParameterizedTest
    @CsvSource({
        "0, N",
        "1, I",
        "4, IV",
        "5, V",
        "6, VI",
        "9, IX",
        "10, X",
        "11, XI",
        "40, XL",
        "46, XLVI",
        "49, XLIX",
        "50, L",
        "61, LXI",
        "90, XC",
        "99, XCIX",
        "100, C",
        "149, CXLIX",
        "400, CD",
        "489, CDLXXXIX",
        "499, CDXCIX",
        "500, D",
        "543, DXLIII",
        "800, DCCC",
        "900, CM",
        "1000, M",
        "1999, MCMXCIX",
        "2000, MM",
        "2957, MMCMLVII"
    })
    void task4Test(Integer number, String expected) {
        ConverterToRoman.initRomanNumbers();
        String actual = ConverterToRoman.convertToRoman(number);
        assertEquals(actual, expected);
    }
}
