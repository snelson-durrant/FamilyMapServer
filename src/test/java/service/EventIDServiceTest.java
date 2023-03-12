package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import response.EventIDResponse;
import response.EventResponse;
import response.RegisterResponse;

import static org.junit.jupiter.api.Assertions.*;

public class EventIDServiceTest {

    private EventIDService eventIDService;
    private EventIDResponse eventIDResponse;
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
        eventIDService = new EventIDService();
    }

    @AfterEach
    public void tearDown() {

        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    public void eventIDPass() {

        EventService eventService = new EventService();
        EventResponse eventResponse = eventService.event(registerResponse.getAuthtoken());
        String eventID = eventResponse.getData()[0].getEventID();

        eventIDResponse = eventIDService.eventID(registerResponse.getAuthtoken(), eventID);
        assertEquals(registerResponse.getUsername(), eventIDResponse.getAssociatedUsername());
        assertEquals("birth", eventIDResponse.getEventType());
        assertTrue(eventIDResponse.isSuccess());
    }

    @Test
    public void eventIDFail() {

        eventIDResponse = eventIDService.eventID("random authtoken", registerResponse.getPersonID());
        assertNull(eventIDResponse.getAssociatedUsername());
        assertFalse(eventIDResponse.isSuccess());

        eventIDResponse = eventIDService.eventID(registerResponse.getAuthtoken(), "random id");
        assertNull(eventIDResponse.getAssociatedUsername());
        assertFalse(eventIDResponse.isSuccess());
    }

}
