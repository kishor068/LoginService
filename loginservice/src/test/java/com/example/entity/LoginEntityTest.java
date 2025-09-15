package com.example.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoginEntityTest {
    //The Validator object is used to programmatically validate Java bean objects against their declared validation constraints such as @NotNull, @Size, @Email,
    public static Validator validator;
    @BeforeAll
    static  void setupValidatorInstance(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator=factory.getValidator();
    }

    @Test
    @DisplayName("validation should pass ")
    void entityUservalidationpass(){
        LoginEntity loginEntity=new LoginEntity();
        loginEntity.setUsername("deepthi");
        loginEntity.setPassword("Qwert!23");

        Set<ConstraintViolation<LoginEntity>> violations =validator.validate(loginEntity);
        assertTrue(violations.isEmpty(),"there are no violations");
    //This call returns violations against constraints defined in the entity, which tests can assert for correctness.
    }

    @Test
    void entityUservalidationfails(){
        LoginEntity loginEntity =new LoginEntity();
        loginEntity.setUsername("");
        loginEntity.setPassword("Qwert!23");

        Set<ConstraintViolation<LoginEntity>> violations =validator.validate(loginEntity);
        assertFalse(violations.isEmpty(),"error u name cannot be empty ");

    }
// write a test case to pass password
    @Test
    void entitypassvalidationpass(){
        LoginEntity loginEntity =new LoginEntity();
        loginEntity.setUsername("new user");
        loginEntity.setPassword("Qwert");

        Set<ConstraintViolation<LoginEntity>> violations =validator.validate(loginEntity);
        assertFalse(violations.isEmpty(),"error u name cannot be empty ");

    }

// write all posible test cases

}
