package calculator.model;

import java.util.List;

public class Calculator {

    public int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }
}
