package calculator.validator;

public class CustomDelimiterValidator {

    private static final String MISSING_DELIMITER_END_MARK = "\\n을 입력해 커스텀 구분자 입력을 마쳐주세요.";

    public void validateFormat(String input) {
        if (!input.contains("\\n")) {
            throw new IllegalArgumentException(MISSING_DELIMITER_END_MARK);
        }
    }
}
