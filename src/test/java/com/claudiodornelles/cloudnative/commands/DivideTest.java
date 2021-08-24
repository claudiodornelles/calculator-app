package com.claudiodornelles.cloudnative.commands;

import com.claudiodornelles.cloudnative.annotation.AppConfig;
import com.claudiodornelles.cloudnative.interfaces.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DivideTest {
    
    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    private final Operation divide = (Operation) applicationContext.getBean("divide");
    
    @Test
    public void shouldBeAbleToDivide() {
        assertAll(
                () -> assertDoesNotThrow(() -> divide.execute(1d, 1d)),
                () -> assertEquals(1, divide.execute(1d, 1d).getResult())
        );
    }
    
    @Test
    public void shouldThrowExceptionTryingToDivideByZero(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> divide.execute(1d, 0d));
        assertEquals("Cannot divide a number by zero", exception.getMessage());
    }
    
    @Test
    public void shouldThrowExceptionTryingToDivideWithNullArguments(){
        IllegalArgumentException exceptionFirstParameter = assertThrows(IllegalArgumentException.class, () -> divide.execute(null, 0d));
        IllegalArgumentException exceptionSecondParameter = assertThrows(IllegalArgumentException.class, () -> divide.execute(0d, null));
        assertAll(
                () -> assertEquals("Null parameters given", exceptionFirstParameter.getMessage()),
                () -> assertEquals("Null parameters given", exceptionSecondParameter.getMessage())
        );
    }
}