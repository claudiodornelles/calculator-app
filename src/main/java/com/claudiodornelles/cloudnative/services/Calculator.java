package com.claudiodornelles.cloudnative.services;

import com.claudiodornelles.cloudnative.commands.enums.OperationType;
import com.claudiodornelles.cloudnative.interfaces.Operation;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Calculator {
    
    private final List<Operation> operationsHistory = new ArrayList<>();
    private final ApplicationContext applicationContext;
    
    public Calculator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    public Double execute(@NonNull OperationType operation, @NonNull Double firstTerm, @NonNull Double secondTerm){
        Operation requestedOperation = (Operation) applicationContext.getBean(String.valueOf(operation).toLowerCase());
        requestedOperation.execute(firstTerm, secondTerm);
        operationsHistory.add(requestedOperation);
        return requestedOperation.getResult();
    }
    
    public List<Operation> getOperationsHistory() {
        return operationsHistory;
    }
}