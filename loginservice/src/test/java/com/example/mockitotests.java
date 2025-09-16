package com.example;


import com.example.controller.LoginController;
import com.example.entity.LoginEntity;
import com.example.service.LoginService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class mockitotests {
    @Mock
    private LoginService loginService ;
    @InjectMocks
    private LoginController loginController;

    @Test
    @DisplayName("Login Success")
    public void testLoginSuccess() {
        LoginEntity loginEntity = new LoginEntity(2L, "Kishorraj", "Qwerty123!@#");

        when(loginService.Login("Kishorraj", "Qwerty123!@#")).thenReturn(true);

        ResponseEntity<String> response = loginController.loginUser(loginEntity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login Successful", response.getBody());  // match controller spelling
    }

    @Test
    @DisplayName("Login Failure")
    public void testLoginFailure() {
        LoginEntity loginEntity = new LoginEntity(2L, "wrongUser", "wrongPass");

        when(loginService.Login( "wrongUser", "wrongPass")).thenReturn(false);

        ResponseEntity<String> response = loginController.loginUser(loginEntity);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password", response.getBody());
    }
}


