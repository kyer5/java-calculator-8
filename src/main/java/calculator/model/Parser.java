package calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String DEFAULT_DELIMITER = "[,:]";
    private final String input;

    public Parser(String input) {
        this.input = input;
    }

    public List<Integer> parse() {
        // 1. 구분자 추출
        DelimiterExtractor delimiterExtractor = new DelimiterExtractor();
        String customDelimiter = delimiterExtractor.extractCustomDelimiter(input);

        // 2. 패턴 생성
        String pattern = buildPattern(customDelimiter);

        // 3. 문자열 전처리 (커스텀 구분자 적용 문자열일시, Prefix를 제거)
        String cleanedInput = preprocessInput(input, customDelimiter);

        // 4. 구분자로 문자열을 분리
        Separator separator = new Separator(pattern);
        String[] separatedInput = separator.separateInputValue(cleanedInput);

        // 5. 분리된 문자들을 정수형으로 변환
        return convertToNumbers(separatedInput);
    }

    private String buildPattern(String customDelimiter) {
        return customDelimiter != null ? "[,:" + customDelimiter + "]" : DEFAULT_DELIMITER;
    }

    private String preprocessInput(String input, String customDelimiter) {
        if (customDelimiter != null) {
            return removePrefix(input);
        }
        return input;
    }

    private String removePrefix(String input) {
        int startIndex = input.indexOf("\\n") + 2;
        return input.substring(startIndex);
    }

    private List<Integer> convertToNumbers(String[] separatedInput) {
        List<Integer> numbers = new ArrayList<>();
        for (String value : separatedInput) {
            value = value.trim();
            numbers.add(value.isEmpty() ? 0 : Integer.parseInt(value));
        }
        return numbers;
    }
}
