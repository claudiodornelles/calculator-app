package com.claudiodornelles.cloudnative.annotation;

import com.claudiodornelles.cloudnative.commands.Divide;
import com.claudiodornelles.cloudnative.commands.Multiply;
import com.claudiodornelles.cloudnative.commands.Power;
import com.claudiodornelles.cloudnative.commands.Subtract;
import com.claudiodornelles.cloudnative.commands.Sum;
import com.claudiodornelles.cloudnative.interfaces.Operation;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Lazy
@Configuration
@ComponentScan(basePackages = "com.claudiodornelles.cloudnative")
public class AppConfig {
    
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Operation sum(){
        return new Sum();
    }
    
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Operation subtract(){
        return new Subtract();
    }
    
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Operation divide(){
        return new Divide();
    }
    
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Operation multiply(){
        return new Multiply();
    }
    
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Operation power(){
        return new Power();
    }
}