package com.predragvucetic.action;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;

@Scanned
public class CalculatorAction extends ConfluenceActionSupport {

    private Float calculationResult;

    public CalculatorAction() {

    }

    public String execute() {
        String firstOperand = getCurrentRequest().getParameter("first-operand");
        String operation = getCurrentRequest().getParameter("operation");
        String secondOperand = getCurrentRequest().getParameter("second-operand");

        Float first = Float.parseFloat(firstOperand);
        Float second = Float.parseFloat(secondOperand);
        if ("addition".equals(operation)) {
            calculationResult = first + second;
        } else if ("subtraction".equals(operation)) {
            calculationResult = first - second;
        } else if ("multiplication".equals(operation)) {
            calculationResult = first * second;
        } else {
            calculationResult = second != 0 ? first / second : Float.POSITIVE_INFINITY;
        }
        return "success";
    }

    public Float getCalculationResult() {
        return calculationResult;
    }
}
