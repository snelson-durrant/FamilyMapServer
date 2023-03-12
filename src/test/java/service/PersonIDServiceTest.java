package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import response.PersonIDResponse;
import response.RegisterResponse;

import static org.junit.jupiter.api.Assertions.*;

public class PersonIDServiceTest {

    private PersonIDService personIDService;
    private PersonIDResponse personIDResponse;
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
        personIDService = new PersonIDService();
    }

    @AfterEach
    public void tearDown() {

        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    public void personIDPass() {

        personIDResponse = personIDService.personID(registerResponse.getAuthtoken(), registerResponse.getPersonID());
        assertEquals(registerResponse.getUsername(), personIDResponse.getAssociatedUsername());
        assertNull(personIDResponse.getSpouseID());
        assertTrue(personIDResponse.isSuccess());
    }

    @Test
    public void personIDFail() {

        personIDResponse = personIDService.personID("random authtoken", registerResponse.getPersonID());
        assertNull(personIDResponse.getAssociatedUsername());
        assertFalse(personIDResponse.isSuccess());

        personIDResponse = personIDService.personID(registerResponse.getAuthtoken(), "random id");
        assertNull(personIDResponse.getAssociatedUsername());
        assertFalse(personIDResponse.isSuccess());
    }

}
