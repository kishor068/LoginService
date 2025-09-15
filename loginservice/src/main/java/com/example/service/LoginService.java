package com.example.service;


import com.example.dao.LoginRepository;
import com.example.entity.LoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginService {
    @Autowired
    public LoginRepository loginRepository;

    public LoginEntity createUser(LoginEntity loginEntity)
    {
        return loginRepository.save(loginEntity);
    }

    public boolean Login(String username, String password)
    {
        return loginRepository.findByUsername(username)
                .map(user->user.getPassword().equals(password))
                .orElse(false);


    }

    public List<LoginEntity> getAll()
    {
        return loginRepository.findAll();
    }
}
