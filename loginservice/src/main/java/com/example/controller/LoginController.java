package com.example.controller;

import com.example.dao.LoginRepository;
import com.example.entity.LoginEntity;
import com.example.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:3000")
@Slf4j
public class LoginController {

//   static Logger logger = LoggerFactory.getLogger(LoginController.class);

@Autowired
    private LoginRepository loginRepository;

@Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginEntity loginEntity)
    {
        boolean auth = loginService.Login(loginEntity.getUsername(),loginEntity.getPassword());
        if(auth)
        {
            return ResponseEntity.ok("Login Successful");
        }
        else {
            return ResponseEntity.status(401)
                    .body("Invalid username or password");
        }
    }

    @GetMapping("/searchusers")
    public ResponseEntity<List<LoginEntity>> getusers()
    {
        return ResponseEntity.ok(loginService.getAll());
    }

    @PostMapping("/adduser")
    public ResponseEntity<LoginEntity> createuser(@RequestBody LoginEntity loginEntity)
    {
        return ResponseEntity.ok(loginService.createUser(loginEntity));
    }

    @GetMapping("/log")
    public String logF1(){
        log.info("In F1");
        return "Welcome to Login Service";
    }
}
