package com.claudiodornelles.cloudnative.commands;

import com.claudiodornelles.cloudnative.interfaces.Operation;
import org.springframework.stereotype.Component;

@Component
public class Divide implements Operation {
    private double dividend, divisor, quotient;
    
    @Override
    public String toString() {
        return "Divide: " + dividend + " / " + divisor + " = " + quotient;
    }
    
    @Override
    public Operation execute(Double dividend, Double divisor) {
        if (dividend == null || divisor == null) throw new IllegalArgumentException("Null parameters given");
        else if (divisor == 0) throw new IllegalArgumentException("Cannot divide a number by zero");
        else {
            this.dividend = dividend;
            this.divisor = divisor;
            this.quotient = dividend / divisor;
            return this;
        }
    }
    
    @Override
    public Double getFirstTerm() {
        return dividend;
    }
    
    @Override
    public Double getSecondTerm() {
        return divisor;
    }
    
    @Override
    public Double getResult() {
        return quotient;
    }
}