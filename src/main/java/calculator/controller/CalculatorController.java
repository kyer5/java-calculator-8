package calculator.controller;

import calculator.model.Calculator;
import calculator.model.Parser;
import calculator.view.Input;
import calculator.view.Output;

public class CalculatorController {

    public void run() {
        Input input = new Input();
        String inputValue = input.readInput();

        Parser parser = new Parser(inputValue);
        Calculator calculator = new Calculator();
        int sum = calculator.calculateSum(parser.parse());

        Output output = new Output();
        output.printCalculateResult(sum);
    }
}
