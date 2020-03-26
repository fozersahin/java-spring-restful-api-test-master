package uk.co.huntersix.spring.rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.impl.PersonDataService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonDataService personDataService;

    @Test
    public void shouldReturnPersonFromService() throws Exception {

        Person person = new Person("Mary", "Smith");

        when(personDataService.findPerson(any(), any())).thenReturn(Arrays.asList(person));
        this.mockMvc.perform(get("/person/smith/mary"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].firstName").value("Mary"))
                .andExpect(jsonPath("$[0].lastName").value("Smith"));
    }

    @Test
    public void shouldReturnNotFoundFromService() throws Exception {
        this.mockMvc.perform(get("/person/smith/finlay"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnPersonListByLastName() throws Exception {
        String lastname = "Smith";
        Person person1 = new Person("Mary", lastname);
        Person person2 = new Person("Brian", lastname);
        when(personDataService.findPersonByLastName(lastname)).thenReturn(Arrays.asList(person1, person2));
        this.mockMvc.perform(get("/person/lastName/Smith"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Mary"))
                .andExpect(jsonPath("$[1].firstName").value("Brian"));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        String lastname = "Smith";
        Person person1 = new Person("Mary", lastname);
        Person person2 = new Person("Brian", lastname);
        when(personDataService.findPersonByLastName(lastname)).thenReturn(Arrays.asList(person1, person2));
        this.mockMvc.perform(get("/person/lastName/Ozersahin"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}