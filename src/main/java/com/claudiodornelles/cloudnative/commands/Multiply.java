package com.claudiodornelles.cloudnative.commands;

import com.claudiodornelles.cloudnative.interfaces.Operation;
import org.springframework.stereotype.Component;

@Component
public class Multiply implements Operation {
    private double firstFactor;
    private double secondFactor;
    private double product;
    
    @Override
    public String toString() {
        return "Multiply: " + firstFactor + " * " + secondFactor + " = " + product;
    }
    
    @Override
    public Operation execute(Double firstFactor, Double secondFactor) {
        if (firstFactor == null || secondFactor == null) throw new IllegalArgumentException("Null parameters given");
        else {
            this.firstFactor = firstFactor;
            this.secondFactor = secondFactor;
            this.product = firstFactor * secondFactor;
            return this;
        }
    }
    
    @Override
    public Double getFirstTerm() {
        return firstFactor;
    }
    
    @Override
    public Double getSecondTerm() {
        return secondFactor;
    }
    
    @Override
    public Double getResult() {
        return product;
    }
}