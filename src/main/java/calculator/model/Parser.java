package calculator.model;

public class Parser {

    private static final String DEFAULT_DELIMITER = "[,:]";
    private final String input;

    public Parser(String input) {
        this.input = input;
    }

    public void parse() {
        // 1. 구분자 추출
        DelimiterExtractor delimiterExtractor = new DelimiterExtractor();
        String customDelimiter = delimiterExtractor.extractCustomDelimiter(input);

        // 2. 패턴 생성
        String pattern = buildPattern(customDelimiter);

        // 3. 문자열 전처리 (커스텀 구분자 적용 문자열일시, Prefix를 제거)
        String cleanedInput = preprocessInput(input, customDelimiter);
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
}
