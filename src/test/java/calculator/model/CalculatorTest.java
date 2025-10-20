package calculator.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    @DisplayName("여러 숫자의 합을 계산한다")
    void calculateSum() {
        // given
        Calculator calculator = new Calculator();
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // when
        int result = calculator.calculateSum(numbers);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("빈 리스트는 0으로 간주한다.")
    void calculateSumWithEmptyList() {
        // given
        Calculator calculator = new Calculator();
        List<Integer> numbers = Collections.emptyList();

        // when
        int result = calculator.calculateSum(numbers);

        // then
        assertThat(result).isEqualTo(0);
    }
}