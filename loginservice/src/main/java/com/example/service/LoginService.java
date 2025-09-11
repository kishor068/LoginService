package com.example.service;


import com.example.dao.LoginRepository;
import com.example.entity.LoginEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LoginService {
    @Autowired
    public LoginRepository loginRepository;

    public LoginEntity createUser(LoginEntity loginEntity)
    {
        return loginRepository.save(loginEntity);
    }

    public boolean Login(String username, String password)
    {
        return loginRepository.findByUserName(username)
                .map(user->user.getPassword().equals(password))
                .orElse(false);


    }

    public List<LoginEntity> getAll()
    {
        return loginRepository.findAll();
    }
}
