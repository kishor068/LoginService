package com.example.controller;

import com.example.dao.LoginRepository;
import com.example.entity.LoginEntity;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:3000")
public class LoginController {

//    @Autowired
    private LoginRepository loginRepository;

//    @Autowired
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
}
