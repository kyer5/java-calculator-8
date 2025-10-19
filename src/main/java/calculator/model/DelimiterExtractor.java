package calculator.model;

public class DelimiterExtractor {

    public String extractCustomDelimiter(String input) {
        if (hasCustomDelimiter(input)) {
            int startIndex = 2;
            int endIndex = input.indexOf("\\n");
            return input.substring(startIndex, endIndex);
        }
        return null;
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith("//") && input.contains("\\n");
    }
}
