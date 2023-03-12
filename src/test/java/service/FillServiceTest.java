package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import response.RegisterResponse;
import response.TableModResponse;

import static org.junit.jupiter.api.Assertions.*;

public class FillServiceTest {

    private FillService fillService;
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

        RegisterService registerService = new RegisterService();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("john");
        registerRequest.setPassword("1234");
        registerRequest.setEmail("email@email.com");
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Smith");
        registerRequest.setGender("m");
        registerResponse = registerService.register(registerRequest);
        fillService = new FillService();
    }

    @AfterEach
    public void tearDown() {

        clearService.clear();
    }

    @Test
    public void fillPass() {

        fillService.fill(registerRequest.getUsername(), 1);
        assertEquals(3, personService.person(registerResponse.getAuthtoken()).getData().length);
        assertEquals(7, eventService.event(registerResponse.getAuthtoken()).getData().length);

        fillService.fill(registerRequest.getUsername(), 4);
        assertEquals(31, personService.person(registerResponse.getAuthtoken()).getData().length);
        assertEquals(91, eventService.event(registerResponse.getAuthtoken()).getData().length);
    }

    @Test
    public void fillFail() {

        TableModResponse fillResponse = fillService.fill("random username", 1);
        assertFalse(fillResponse.isSuccess());
        fillResponse = fillService.fill(registerResponse.getUsername(), -1);
        assertFalse(fillResponse.isSuccess());
        assertEquals(31, personService.person(registerResponse.getAuthtoken()).getData().length);
        assertEquals(91, eventService.event(registerResponse.getAuthtoken()).getData().length);
    }

}
