package calculator.validator;

public class NumberValidator {

    private static final String ONLY_NATURAL_NUMBER_ALLOWED = "자연수만 입력할 수 있습니다.";

    public void validateNumber(String[] separatedInput) {
        for (String token : separatedInput) {
            if (token.contains("-") || token.contains(".") || token.contains("0")) {
                throw new IllegalArgumentException(ONLY_NATURAL_NUMBER_ALLOWED);
            }
        }
    }
}
