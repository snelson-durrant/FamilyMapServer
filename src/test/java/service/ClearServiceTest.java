package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import response.RegisterResponse;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest {

    private ClearService clearService;
    private RegisterService registerService;
    private RegisterRequest registerRequest;
    private RegisterResponse registerResponse;
    private EventService eventService = new EventService();
    private PersonService personService = new PersonService();
    String testAuthtoken;

    @BeforeEach
    public void setUp() {

        clearService = new ClearService();
        registerService = new RegisterService();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("john");
        registerRequest.setPassword("1234");
        registerRequest.setEmail("email@email.com");
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Smith");
        registerRequest.setGender("m");
    }

    @Test
    public void clearPass() {

        registerResponse = registerService.register(registerRequest);
        testAuthtoken = registerResponse.getAuthtoken();

        clearService.clear();
        assertNull(eventService.event(testAuthtoken).getData());
        assertNull(personService.person(testAuthtoken).getData());
    }

    @Test
    public void clearPassAlt() {

        // clears an empty database
        clearService.clear();
        assertNull(eventService.event(testAuthtoken).getData());
        assertNull(personService.person(testAuthtoken).getData());
    }

}
