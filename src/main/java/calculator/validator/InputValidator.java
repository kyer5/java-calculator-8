package calculator.validator;

public class InputValidator {

    private static final String INVALID_DELIMITER = "유효하지 않은 구분자입니다.";
    private static final String EMPTY_INPUT = "입력값이 비어 있습니다.";

    public void validateInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT);
        }
    }

    public void validateInputDelimiter(String input, String customDelimiter) {
        String[] tokens = input.split("");

        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0)) || token.equals("-") || token.equals(".")) {
                continue;
            }

            if (customDelimiter != null) {
                if (!token.equals(",") && !token.equals(":") && !token.equals(customDelimiter)) {
                    throw new IllegalArgumentException(INVALID_DELIMITER);
                }
            } else {
                if (!token.equals(",") && !token.equals(":")) {
                    throw new IllegalArgumentException(INVALID_DELIMITER);
                }
            }
        }
    }
}
