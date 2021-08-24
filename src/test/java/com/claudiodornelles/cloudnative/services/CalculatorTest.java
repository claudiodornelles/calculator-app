package com.claudiodornelles.cloudnative.services;

import com.claudiodornelles.cloudnative.annotation.AppConfig;
import com.claudiodornelles.cloudnative.commands.Divide;
import com.claudiodornelles.cloudnative.commands.Multiply;
import com.claudiodornelles.cloudnative.commands.Power;
import com.claudiodornelles.cloudnative.commands.Subtract;
import com.claudiodornelles.cloudnative.commands.Sum;
import com.claudiodornelles.cloudnative.commands.enums.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    private final Calculator calculator = new Calculator(applicationContext);
    
    @Test
    public void shouldBeAbleToExecuteOperations(){
        assertAll(
                () -> assertDoesNotThrow(() -> calculator.execute(OperationType.SUM, 1d, 1d)),
                () -> assertDoesNotThrow(() -> calculator.execute(OperationType.SUBTRACT, 1d, 1d)),
                () -> assertDoesNotThrow(() -> calculator.execute(OperationType.MULTIPLY, 1d, 1d)),
                () -> assertDoesNotThrow(() -> calculator.execute(OperationType.DIVIDE, 1d, 1d)),
                () -> assertDoesNotThrow(() -> calculator.execute(OperationType.POWER, 1d, 1d))
        );
    }
    
    @Test
    public void shouldBeAbleToAddOperationsToHistoryList() {
        assertAll(
                () -> assertDoesNotThrow(() -> calculator.execute(OperationType.SUM, 2d, 3d)),
                () -> assertDoesNotThrow(calculator::getOperationsHistory),
                () -> assertEquals(1, calculator.getOperationsHistory().size()),
                () -> Assertions.assertEquals(2, calculator.getOperationsHistory().get(0).getFirstTerm()),
                () -> Assertions.assertEquals(3, calculator.getOperationsHistory().get(0).getSecondTerm()),
                () -> Assertions.assertEquals(5, calculator.getOperationsHistory().get(0).getResult())
        );
    }
    
    @Test
    public void shouldBeAbleToAddMultipleInstancesOfOperationToHistoryList() {
        assertAll(
                () -> assertDoesNotThrow(() -> calculator.execute(OperationType.SUM, 2d, 3d)),
                () -> assertDoesNotThrow(() -> calculator.execute(OperationType.SUM, 3d, 4d)),
                () -> assertDoesNotThrow(calculator::getOperationsHistory),
                () -> assertEquals(2, calculator.getOperationsHistory().size()),
                () -> Assertions.assertEquals(2, calculator.getOperationsHistory().get(0).getFirstTerm()),
                () -> Assertions.assertEquals(3, calculator.getOperationsHistory().get(0).getSecondTerm()),
                () -> Assertions.assertEquals(5, calculator.getOperationsHistory().get(0).getResult()),
                () -> Assertions.assertEquals(3, calculator.getOperationsHistory().get(1).getFirstTerm()),
                () -> Assertions.assertEquals(4, calculator.getOperationsHistory().get(1).getSecondTerm()),
                () -> Assertions.assertEquals(7, calculator.getOperationsHistory().get(1).getResult())
        );
    }
    
    @Test
    public void shouldReturnOperationsHistory() {
        calculator.execute(OperationType.SUM, 1d, 1d);
        calculator.execute(OperationType.SUBTRACT, 1d, 1d);
        calculator.execute(OperationType.MULTIPLY, 1d, 1d);
        calculator.execute(OperationType.DIVIDE, 1d, 1d);
        calculator.execute(OperationType.POWER, 1d, 1d);
        assertAll(
                () -> assertDoesNotThrow(calculator::getOperationsHistory),
                () -> assertEquals(5, calculator.getOperationsHistory().size()),
                () -> Assertions.assertEquals(Sum.class, calculator.getOperationsHistory().get(0).getClass()),
                () -> Assertions.assertEquals(Subtract.class, calculator.getOperationsHistory().get(1).getClass()),
                () -> Assertions.assertEquals(Multiply.class, calculator.getOperationsHistory().get(2).getClass()),
                () -> Assertions.assertEquals(Divide.class, calculator.getOperationsHistory().get(3).getClass()),
                () -> Assertions.assertEquals(Power.class, calculator.getOperationsHistory().get(4).getClass())
        );
    }
}