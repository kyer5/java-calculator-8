package calculator.validator;

public class InputValidator {

    private static final String EMPTY_INPUT = "입력값이 비어 있습니다.";

    public void validateInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT);
        }
    }

}
