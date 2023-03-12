package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import response.PersonResponse;
import response.RegisterResponse;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    private PersonService personService;
    private PersonResponse personResponse;
    private RegisterResponse registerResponse;

    @BeforeEach
    public void setUp() {

        RegisterService registerService = new RegisterService();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("john");
        registerRequest.setPassword("1234");
        registerRequest.setEmail("email@email.com");
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Smith");
        registerRequest.setGender("m");
        registerResponse = registerService.register(registerRequest);
        personService = new PersonService();
    }

    @AfterEach
    public void tearDown() {

        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    public void personPass() {

        personResponse = personService.person(registerResponse.getAuthtoken());
        assertEquals(31, personResponse.getData().length);
        assertTrue(personResponse.isSuccess());
    }

    @Test
    public void personFail() {

        personResponse = personService.person("random authtoken");
        assertNull(personResponse.getData());
        assertFalse(personResponse.isSuccess());
    }

}
