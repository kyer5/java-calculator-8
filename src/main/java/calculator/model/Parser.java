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
    }

    private String buildPattern(String customDelimiter) {
        return customDelimiter != null ? "[,:" + customDelimiter + "]" : DEFAULT_DELIMITER;
    }
}
