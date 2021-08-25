package com.claudiodornelles.cloudnative.commands;

import com.claudiodornelles.cloudnative.interfaces.Operation;
import org.springframework.stereotype.Component;

@Component
public class Power implements Operation {
    private double base;
    private double exponent;
    private double result;
    
    @Override
    public String toString() {
        return "Power: " + base + " ^ " + exponent + " = " + result;
    }
    
    @Override
    public Operation execute(Double base, Double exponent) {
        if (base == null || exponent == null) throw new IllegalArgumentException("Null parameters given");
        else {
            this.base = base;
            this.exponent = exponent;
            this.result = Math.pow(base, exponent);
            return this;
        }
    }
    
    @Override
    public Double getFirstTerm() {
        return base;
    }
    
    @Override
    public Double getSecondTerm() {
        return exponent;
    }
    
    @Override
    public Double getResult() {
        return result;
    }
}