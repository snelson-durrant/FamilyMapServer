package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import response.EventResponse;
import response.RegisterResponse;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {

    private EventService eventService;
    private EventResponse eventResponse;
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
        eventService = new EventService();
    }

    @AfterEach
    public void tearDown() {

        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    public void eventPass() {

        eventResponse = eventService.event(registerResponse.getAuthtoken());
        assertEquals(91, eventResponse.getData().length);
        assertTrue(eventResponse.isSuccess());
    }

    @Test
    public void eventFail() {

        eventResponse = eventService.event("random authtoken");
        assertNull(eventResponse.getData());
        assertFalse(eventResponse.isSuccess());
    }

}
