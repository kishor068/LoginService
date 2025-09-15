package com.example;

import com.example.controller.LoginController;
import com.example.dao.LoginRepository;
import com.example.entity.LoginEntity;
import com.example.service.LoginService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")



public class LoginTest {
    @Autowired
    public LoginRepository loginRepository;
    @Autowired
    public LoginService loginService;
    @Autowired
    public LoginController loginController;

    @Test
    @DisplayName("Check test two")
    public void t2() throws Exception {
        System.out.println("test two");
    }

    @Test
    @Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public  void logincheck(){
        //(HttpStatus.OK,loginService.Login("Alice", "Password123!"));
        Boolean res= loginService.Login("Alice", "Password123!");
        assertTrue(res);
    }

    @Test
//    @Disabled
    @DisplayName("FInd Initial records")
    public void findInitialRecords() throws Exception {
        loginRepository.save(new LoginEntity(1L,"Kishor","Qwerty123!@#"));
        assertEquals(1,loginRepository.findAll().size());

    }

    @Test
    @DisplayName("Checking Login Status")
    public void checkLoginStatus()  {
        LoginEntity loginEntity=new LoginEntity(2L,"Kishorraj","Qwerty123!@#");
        loginRepository.save(loginEntity);
        ResponseEntity<String> response = loginController.loginUser(loginEntity);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Login Successful",response.getBody());
    }


    @Test
    @DisplayName("Checking Login Fail Status")
    public void checkLoginFailStatus()  {
        try{
           ResponseEntity<String> l1= loginController.loginUser(new LoginEntity(1L,"Kishor123","Qwerty123!@#"));
//            fail("Login Failed");
           assertEquals(l1.getBody(), "Invalid username or password");

        }catch (Exception ex){

        }
    }

//    @Test
@DisplayName("Checking Login with Parameterized Data")
@ParameterizedTest
@CsvSource({
        "1,Kishor,Qwerty123!@#,true",
        "2,Kishor123,Qwerty123!@#,false"
})


public void parameterizedTestForUser(Long id, String username, String password, boolean expectedSuccess) {

    loginRepository.save(new LoginEntity(1L, "Kishor", "Qwerty123!@#"));
    ResponseEntity<String> response = loginController.loginUser(new LoginEntity(id, username, password));

    if (expectedSuccess) {
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected success login");
        assertEquals("Login Successful", response.getBody());
    } else {
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode(), "Expected failed login");
        assertEquals("Invalid username or password", response.getBody());
    }
}

    @DisplayName("Checking Login with Parameterized Data Fail")
    @ParameterizedTest
    @CsvSource({
            "2,Kishor123,Qwerty123!@#",
            "3,Kishorrdgd123,Qwerty123!@#",
//            "1,Kishor,Qwerty123!@#"
    })


    public void parameterizedTestForUserFail(Long id, String username, String password ) {

        loginRepository.save(new LoginEntity(1L, "Kishor", "Qwerty123!@#"));
        loginRepository.save(new LoginEntity(2L, "Mahesha", "Qwerty123!@#"));

        ResponseEntity<String> response = loginController.loginUser(new LoginEntity(id, username, password));

            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode(), "Expected failed login");
            assertEquals("Invalid username or password", response.getBody());
    }

    @DisplayName("Checking Login with Parameterized Data pass")
    @ParameterizedTest
    @CsvSource({
            "1,Kishor,Qwerty123!@#",
            "2,Rajesha,Qwerty123!@#"

    })

    public void parameterizedTestForUserTrue(Long id, String username, String password ) {
        // Arrange: ensure valid user exists in DB
        loginRepository.save(new LoginEntity(1L, "Kishor", "Qwerty123!@#"));
        loginRepository.save(new LoginEntity(2L, "Rajesha", "Qwerty123!@#"));
        // Act
        ResponseEntity<String> response = loginController.loginUser(new LoginEntity(id, username, password));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected success login");
        assertEquals("Login Successful", response.getBody());


    }

    @BeforeEach
    public void testBeforeEach(){
//        loginService.createUser(new LoginEntity(2L,"Kishor","Qwerty123!@#"));
    }


    @AfterEach
    public void testAfterEach(){
        System.out.println("testAfterEach");
    }


    @AfterAll
    public static void testAfterAll(){
        System.out.println("testAfterAll");
    }

    @BeforeAll
    public static void testBeforeAll(){
        System.out.println("testBeforeAll");
    }

//    @Disabled("FEATURE NOT READY!")
//    @Test
//    public void testDisabled(){
//        System.out.println("testDisabled");
//    }

}
