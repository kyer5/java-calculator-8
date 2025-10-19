package calculator.model;

public class Separator {

    private final String pattern;

    public Separator(String pattern) {
        this.pattern = pattern;
    }

    public String[] separateInputValue(String cleanedInput) {
        return cleanedInput.split(pattern);
    }
}
