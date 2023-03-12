package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import request.RegisterRequest;
import response.RegisterResponse;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private LoginService loginService;
    private LoginRequest loginRequest;
    private RegisterResponse loginResponse;
    private RegisterService registerService;
    private RegisterRequest registerRequest;
    private ClearService clearService = new ClearService();

    @BeforeEach
    public void setUp() {

        registerService = new RegisterService();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("john");
        registerRequest.setPassword("1234");
        registerRequest.setEmail("email@email.com");
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Smith");
        registerRequest.setGender("m");
        registerService.register(registerRequest);
        loginService = new LoginService();
    }

    @AfterEach
    public void tearDown() {

        clearService.clear();
    }

    @Test
    public void loginPass() {

        loginRequest = new LoginRequest();
        loginRequest.setUsername(registerRequest.getUsername());
        loginRequest.setPassword(registerRequest.getPassword());
        loginResponse = loginService.login(loginRequest);
        assertNotNull(loginResponse.getAuthtoken());
        assertNotNull(loginResponse.getPersonID());
        assertEquals(registerRequest.getUsername(), loginResponse.getUsername());
    }

    @Test
    public void loginFail() {

        loginRequest = new LoginRequest();
        loginRequest.setUsername(registerRequest.getUsername());
        loginRequest.setPassword("wrong password");
        loginResponse = loginService.login(loginRequest);
        assertNull(loginResponse.getAuthtoken());
        assertNull(loginResponse.getUsername());
        assertNull(loginResponse.getPersonID());
        assertFalse(loginResponse.isSuccess());

        loginRequest.setUsername("wrong username");
        loginRequest.setPassword(registerRequest.getPassword());
        loginResponse = loginService.login(loginRequest);
        assertNull(loginResponse.getAuthtoken());
        assertNull(loginResponse.getUsername());
        assertNull(loginResponse.getPersonID());
        assertFalse(loginResponse.isSuccess());
    }

}
