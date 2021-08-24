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

public class SubtractTest {
    
    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    private final Operation subtract = (Operation) applicationContext.getBean("subtract");
    
    @Test
    public void shouldBeAbleToSubtract() {
        assertAll(
                () -> assertDoesNotThrow(() -> subtract.execute(1d, 1d)),
                () -> assertEquals(0, subtract.execute(1d, 1d).getResult())
        );
    }
    
    @Test
    public void shouldThrowExceptionTryingToDivideWithNullArguments(){
        IllegalArgumentException exceptionFirstParameter = assertThrows(IllegalArgumentException.class, () -> subtract.execute(null, 1d));
        IllegalArgumentException exceptionSecondParameter = assertThrows(IllegalArgumentException.class, () -> subtract.execute(1d, null));
        assertAll(
                () -> assertEquals("Null parameters given", exceptionFirstParameter.getMessage()),
                () -> assertEquals("Null parameters given", exceptionSecondParameter.getMessage())
        );
    }
}