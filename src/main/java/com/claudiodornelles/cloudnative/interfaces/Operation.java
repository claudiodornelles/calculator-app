package com.claudiodornelles.cloudnative.interfaces;

public interface Operation {
    Operation execute(Double firstTerm, Double secondTerm);
    Double getFirstTerm();
    Double getSecondTerm();
    Double getResult();
}