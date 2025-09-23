package com.example.StepDefinition;

import com.example.entity.LoginEntity;
import com.example.service.LoginService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginSteps {

//    @Autowired
    private LoginService loginService;

    private boolean loginResult;

    @Given("a user exists with username {string} and password {string}")
    public void a_user_exists_with_username_and_password(String username, String password) {
        System.out.println("Creating user: " + username + " / " + password);
        LoginEntity loginEntity = new LoginEntity(username,password);
        loginService.createUser(loginEntity);
    }
    @When("the user tries to login with username {string} and password {string}")
    public void the_user_tries_to_login_with_username_and_password(String username, String password) {
        loginResult = loginService.Login(username, password);
    }
    @Then("the login should be successful")
    public void the_login_should_be_successful() {
        assertTrue(loginResult);
    }

    @Then("the login should fail")
    public void the_login_should_fail() {
        assertFalse(loginResult);
    }


}
