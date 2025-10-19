package calculator.validator;

public class CustomDelimiterValidator {

    private static final int MAX_DELIMITER_LENGTH = 1;
    private static final String MISSING_DELIMITER_END_MARK = "\\n을 입력해 커스텀 구분자 입력을 마쳐주세요.";
    private static final String TOO_LONG_CUSTOM_DELIMITER = "두 글자 이상으로 커스텀 구분자를 지정할 수 없습니다.";
    private static final String DEFAULT_DELIMITER_REDECLARATION = "기본 구분자(',', ':')를 커스텀 구분자로 다시 지정할 수 없습니다.";
    private static final String NUMERIC_CUSTOM_DELIMITER = "숫자는 구분자로 지정할 수 없습니다.";
    private static final String BLANK_CUSTOM_DELIMITER = "공백은 구분자로 지정할 수 없습니다.";

    public void validateFormat(String input) {
        if (!input.contains("\\n")) {
            throw new IllegalArgumentException(MISSING_DELIMITER_END_MARK);
        }

        if (input.matches("^//\\\\n.*")) {
            throw new IllegalArgumentException(BLANK_CUSTOM_DELIMITER);
        }
    }

    public void validateDelimiter(String customDelimiter) {
        if (customDelimiter.length() > MAX_DELIMITER_LENGTH) {
            throw new IllegalArgumentException(TOO_LONG_CUSTOM_DELIMITER);
        }

        if (customDelimiter.equals(",") || customDelimiter.equals(":")) {
            throw new IllegalArgumentException(DEFAULT_DELIMITER_REDECLARATION);
        }

        if (Character.isDigit(customDelimiter.charAt(0))) {
            throw new IllegalArgumentException(NUMERIC_CUSTOM_DELIMITER);
        }

        if (customDelimiter.equals(" ")) {
            throw new IllegalArgumentException(BLANK_CUSTOM_DELIMITER);
        }
    }
}
