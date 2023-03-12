package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import response.RegisterResponse;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterServiceTest {

    private RegisterService registerService;
    private RegisterRequest registerRequest;
    private RegisterResponse registerResponse;
    private ClearService clearService;
    private EventService eventService;
    private PersonService personService;

    @BeforeEach
    public void setUp() {

        clearService = new ClearService();
        eventService = new EventService();
        personService = new PersonService();

        registerService = new RegisterService();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("john");
        registerRequest.setPassword("1234");
        registerRequest.setEmail("email@email.com");
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Smith");
        registerRequest.setGender("m");
    }

    @AfterEach
    public void tearDown() {

        clearService.clear();
    }

    @Test
    public void registerPass() {

        registerResponse = registerService.register(registerRequest);
        assertNotNull(registerResponse.getAuthtoken());
        assertNotNull(registerResponse.getPersonID());
        assertEquals(registerRequest.getUsername(), registerResponse.getUsername());
        assertEquals(31, personService.person(registerResponse.getAuthtoken()).getData().length);
        assertEquals(91, eventService.event(registerResponse.getAuthtoken()).getData().length);
    }

    @Test
    public void registerFail() {

        registerResponse = registerService.register(registerRequest);
        RegisterResponse registerResponse2 = registerService.register(registerRequest);
        assertNull(registerResponse2.getAuthtoken());
        assertNull(registerResponse2.getUsername());
        assertNull(registerResponse2.getPersonID());
        assertFalse(registerResponse2.isSuccess());
    }

}
