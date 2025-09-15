package com.example.service;

import com.example.dao.LoginRepository;
import com.example.entity.LoginEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    //@Mock → creates a fake (mock) LoginRepository.
    //@InjectMocks → creates a real LoginService object and injects the mock repository into it.
    @Mock
    LoginRepository loginRepository;

    @InjectMocks
    LoginService loginService;

    LoginEntity u1;
    LoginEntity u2;

    @BeforeEach
    void setup(){
        u1= new LoginEntity(1L,"Deeps","Deept123@");
        u2= new LoginEntity(2L,"Ansh","Ansh123@");
    }

    @Test
    void createUserTest(){
        when(loginRepository.save(u1)).thenReturn(u1);
        LoginEntity saveduser = loginService.createUser(u1);

        assertNotNull(saveduser);
        assertEquals("Deeps",saveduser.getUsername());
        verify(loginRepository,times(1)).save(u1);
    }

}
