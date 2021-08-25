package com.claudiodornelles.cloudnative.commands;

import com.claudiodornelles.cloudnative.interfaces.Operation;
import org.springframework.stereotype.Component;

@Component
public class Subtract implements Operation {
    private double firstTerm;
    private double secondTerm;
    private double difference;
    
    @Override
    public String toString() {
        return "Subtract: " + firstTerm + " - " + secondTerm + " = " + difference;
    }
    
    @Override
    public Operation execute(Double firstTerm, Double secondTerm) {
        if (firstTerm == null || secondTerm == null) throw new IllegalArgumentException("Null parameters given");
        else {
            this.firstTerm = firstTerm;
            this.secondTerm = secondTerm;
            this.difference = firstTerm - secondTerm;
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
        return difference;
    }
}