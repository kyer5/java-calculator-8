package calculator.model;

import calculator.validator.CustomDelimiterValidator;
import calculator.validator.InputValidator;
import calculator.validator.NumberValidator;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String DEFAULT_DELIMITER = "[,:]";
    private final String input;

    public Parser(String input) {
        this.input = input;
    }

    public List<Integer> parse() {
        InputValidator inputValidator = new InputValidator();
        inputValidator.validateInput(input);

        String customDelimiter = getCustomDelimiter();

        // 2. 패턴 생성
        String pattern = buildPattern(customDelimiter);

        // 3. 문자열 전처리 (커스텀 구분자 적용 문자열일시, Prefix를 제거)
        String cleanedInput = preprocessInput(customDelimiter);

        inputValidator.validateInputDelimiter(cleanedInput, customDelimiter);

        String[] separatedInput = getSeparatedInput(pattern, cleanedInput);

        return convertToNumbers(separatedInput);
    }

    private static String[] getSeparatedInput(String pattern, String cleanedInput) {
        Separator separator = new Separator(pattern);
        String[] separatedInput = separator.separateInputValue(cleanedInput);

        NumberValidator numberValidator = new NumberValidator();
        numberValidator.validateNumber(separatedInput);
        return separatedInput;
    }

    private String getCustomDelimiter() {
        CustomDelimiterValidator customDelimiterValidator = new CustomDelimiterValidator();

        if (input.startsWith("//")) {
            customDelimiterValidator.validateFormat(input);
        }

        // 1. 구분자 추출
        DelimiterExtractor delimiterExtractor = new DelimiterExtractor();
        String customDelimiter = delimiterExtractor.extractCustomDelimiter(input);

        if (customDelimiter != null) {
            customDelimiterValidator.validateDelimiter(customDelimiter);
        }
        return customDelimiter;
    }

    private String buildPattern(String customDelimiter) {
        return customDelimiter != null ? "[,:" + customDelimiter + "]" : DEFAULT_DELIMITER;
    }

    private String preprocessInput(String customDelimiter) {
        if (customDelimiter != null) {
            return removePrefix();
        }
        return input;
    }

    private String removePrefix() {
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
