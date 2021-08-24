package com.claudiodornelles.cloudnative.commands;

import com.claudiodornelles.cloudnative.interfaces.Operation;
import org.springframework.stereotype.Component;

@Component
public class Sum implements Operation {
    private double firstTerm, secondTerm, sum;
    
    @Override
    public String toString() {
        return "Sum: " + firstTerm + " + " + secondTerm + " = " + sum;
    }
    
    @Override
    public Operation execute(Double firstTerm, Double secondTerm) {
        if (firstTerm == null || secondTerm == null) throw new IllegalArgumentException("Null parameters given");
        else {
            this.firstTerm = firstTerm;
            this.secondTerm = secondTerm;
            this.sum = firstTerm + secondTerm;
            return this;
        }
    }
    
    @Override
    public Double getFirstTerm() {
        return firstTerm;
    }
    
    @Override
    public Double getSecondTerm() {
        return secondTerm;
    }
    
    @Override
    public Double getResult() {
        return sum;
    }
}