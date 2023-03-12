package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoadRequest;
import request.LoginRequest;
import response.RegisterResponse;
import model.*;
import response.TableModResponse;

import static org.junit.jupiter.api.Assertions.*;

public class LoadServiceTest {

    private LoadService loadService;
    private LoadRequest loadRequest;
    private LoadRequest invalidLoadRequest;
    private ClearService clearService;
    private EventService eventService;
    private PersonService personService;
    private LoginService loginService;

    @BeforeEach
    public void setUp() {

        User user = new User("john", "1234", "email@email.com", "John",
                "Smith", "m", "4321");
        User invalidUser = new User("john", null, "email@email.com", "John",
                "Smith", "m", "4321");

        Person person = new Person("4321", "john", "John", "Smith",
                "m", null, null, null);

        Event event = new Event("9876", "john", "4321", 0F, 0F,
                "USA", "Provo", "birth", 2000);

        clearService = new ClearService();
        eventService = new EventService();
        personService = new PersonService();
        loginService = new LoginService();

        loadService = new LoadService();
        loadRequest = new LoadRequest();
        User[] users = new User[1];
        users[0] = user;
        loadRequest.setUsers(users);
        Person[] persons = new Person[1];
        persons[0] = person;
        loadRequest.setPersons(persons);
        Event[] events = new Event[1];
        events[0] = event;
        loadRequest.setEvents(events);

        invalidLoadRequest = new LoadRequest();
        User[] invalidUsers = new User[1];
        invalidUsers[0] = invalidUser;
        invalidLoadRequest.setUsers(invalidUsers);
        invalidLoadRequest.setPersons(persons);
        invalidLoadRequest.setEvents(events);
    }

    @AfterEach
    public void tearDown() {

        clearService.clear();
    }

    @Test
    public void loadPass() {

        TableModResponse loadResponse = loadService.load(loadRequest);
        assertTrue(loadResponse.isSuccess());
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("john");
        loginRequest.setPassword("1234");
        RegisterResponse registerResponse = loginService.login(loginRequest);
        assertEquals(1, personService.person(registerResponse.getAuthtoken()).getData().length);
        assertEquals(1, eventService.event(registerResponse.getAuthtoken()).getData().length);
    }

    @Test
    public void loadFail() {

        TableModResponse loadResponse = loadService.load(invalidLoadRequest);
        assertFalse(loadResponse.isSuccess());
    }

}
