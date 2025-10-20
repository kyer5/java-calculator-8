package calculator.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    @DisplayName("쉼표로 구분된 숫자를 파싱한다")
    void parseWithComma() {
        // given
        Parser parser = new Parser("1,2,3");

        // when
        List<Integer> result = parser.parse();

        // then
        assertThat(result).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("콜론으로 구분된 숫자를 파싱한다")
    void parseWithColon() {
        // given
        Parser parser = new Parser("1:2:3");

        // when
        List<Integer> result = parser.parse();

        // then
        assertThat(result).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("쉼표와 콜론을 혼합하여 파싱한다")
    void parseWithMixedDelimiters() {
        // given
        Parser parser = new Parser("1,2:3");

        // when
        List<Integer> result = parser.parse();

        // then
        assertThat(result).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("커스텀 구분자로 파싱한다")
    void parseWithCustomDelimiter() {
        // given
        Parser parser = new Parser("//;\\n1;2;3");

        // when
        List<Integer> result = parser.parse();

        // then
        assertThat(result).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("커스텀 구분자와 기본 구분자를 함께 사용한다")
    void parseWithCustomAndDefault() {
        // given
        Parser parser = new Parser("//;\\n1,2;3:4");

        // when
        List<Integer> result = parser.parse();

        // then
        assertThat(result).containsExactly(1, 2, 3, 4);
    }

    @Test
    @DisplayName("단일 숫자를 파싱한다")
    void parseSingleNumber() {
        // given
        Parser parser = new Parser("42");

        // when
        List<Integer> result = parser.parse();

        // then
        assertThat(result).containsExactly(42);
    }

    @Test
    @DisplayName("빈 입력값은 예외가 발생한다")
    void parseEmptyInput() {
        // given
        Parser parser = new Parser("");

        // when & then
        assertThatThrownBy(() -> parser.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값이 비어 있습니다");
    }

    @Test
    @DisplayName("음수 입력 시 예외가 발생한다")
    void parseNegativeNumber() {
        // given
        Parser parser = new Parser("1,2,-3");

        // when & then
        assertThatThrownBy(() -> parser.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자연수만 입력할 수 있습니다");
    }

    @Test
    @DisplayName("소수 입력 시 예외가 발생한다")
    void parseDecimalNumber() {
        // given
        Parser parser = new Parser("1,2.5,3");

        // when & then
        assertThatThrownBy(() -> parser.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자연수만 입력할 수 있습니다");
    }

    @Test
    @DisplayName("유효하지 않은 구분자 사용 시 예외가 발생한다")
    void parseInvalidDelimiter() {
        // given
        Parser parser = new Parser("1,2&3");

        // when & then
        assertThatThrownBy(() -> parser.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 구분자입니다");
    }
}