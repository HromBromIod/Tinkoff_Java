package edu.project3logAnalyzer;

import java.time.LocalDateTime;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DateTimeToFilterTest {
    static Arguments[] expectations() {
        return new Arguments[] {
            Arguments.of(LocalDateTime.of(1970, 1, 1, 1, 1, 2), false),
            Arguments.of(LocalDateTime.of(1970, 1, 1, 1, 1, 1), true),
            Arguments.of(LocalDateTime.of(1970, 1, 1, 1, 1, 0), true),
        };
    }

    @ParameterizedTest
    @DisplayName("Проверка фильтров")
    @MethodSource("expectations")
    void successful(LocalDateTime dateTime, boolean expects) throws Exception {
        var filter = new DateTimeToFilter(LocalDateTime.of(1970, 1, 1, 1, 1, 1));
        assertThat(filter.test(dateTime)).isEqualTo(expects);
    }
}
