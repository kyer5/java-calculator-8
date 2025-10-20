package calculator.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SeparatorTest {

    @Test
    @DisplayName("기본 구분자로 문자열을 분리한다")
    void separateWithDefaultDelimiters() {
        // given
        Separator separator = new Separator("[,:]");
        String input = "1,2:3";

        // when
        String[] result = separator.separateInputValue(input);

        // then
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("커스텀 구분자로 문자열을 분리한다")
    void separateWithCustomDelimiter() {
        // given
        Separator separator = new Separator("[,:;]");
        String input = "1,2;3:4";

        // when
        String[] result = separator.separateInputValue(input);

        // then
        assertThat(result).containsExactly("1", "2", "3", "4");
    }

    @Test
    @DisplayName("연속된 구분자는 빈 문자열을 포함한다")
    void separateWithConsecutiveDelimiters() {
        // given
        Separator separator = new Separator("[,:]");
        String input = "1,,2";

        // when
        String[] result = separator.separateInputValue(input);

        // then
        assertThat(result).containsExactly("1", "", "2");
    }
}