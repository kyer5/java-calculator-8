package calculator.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DelimiterExtractorTest {

    @Test
    @DisplayName("커스텀 구분자를 추출한다")
    void extractCustomDelimiter() {
        // given
        DelimiterExtractor extractor = new DelimiterExtractor();
        String input = "//;\\n1;2;3";

        // when
        String result = extractor.extractCustomDelimiter(input);

        // then
        assertThat(result).isEqualTo(";");
    }

    @Test
    @DisplayName("커스텀 구분자가 없으면 null을 반환한다")
    void extractWithoutCustomDelimiter() {
        // given
        DelimiterExtractor extractor = new DelimiterExtractor();
        String input = "1,2,3";

        // when
        String result = extractor.extractCustomDelimiter(input);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("//로 시작하지만 \\n이 없으면 null을 반환한다")
    void extractWithoutNewline() {
        // given
        DelimiterExtractor extractor = new DelimiterExtractor();
        String input = "//;123";

        // when
        String result = extractor.extractCustomDelimiter(input);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("다양한 종류의 커스텀 구분자를 추출한다")
    void extractVariousDelimiters() {
        // given
        DelimiterExtractor extractor = new DelimiterExtractor();

        // when & then
        assertThat(extractor.extractCustomDelimiter("//|\\n1|2")).isEqualTo("|");
        assertThat(extractor.extractCustomDelimiter("//*\\n1*2")).isEqualTo("*");
        assertThat(extractor.extractCustomDelimiter("//+\\n1+2")).isEqualTo("+");
    }
}