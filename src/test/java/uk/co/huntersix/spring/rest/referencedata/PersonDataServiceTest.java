package uk.co.huntersix.spring.rest.referencedata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.huntersix.spring.rest.exception.PersonNotFoundException;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.impl.PersonDataService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonDataServiceTest {

    @Autowired
    PersonDataService personDataService;

    @Test
    public void findPerson() {
        List<Person> personList = personDataService.findPerson("Smith", "Marry");
        assertNotNull(personList);
    }

    @Test
    public void findPersonByLastName() {
        List<Person> personList = personDataService.findPersonByLastName("Smith");
        assertNotNull(personList);
    }

    @Test
    public void addPerson() {
        personDataService.addPerson(new Person("Bond", "James"));
        List<Person> personList = personDataService.findPerson("Bond", "James");
        assertNotNull(personList);
    }

    @Test
    public void updateFirstName() {
        Person person = personDataService.updateFirstName(new Long(1), "Jane");

        Person person1 = new Person("Jane", "Smith");

        assertEquals(person.getFirstName() , person1.getFirstName());
        assertEquals(person.getLastName() , person1.getLastName());
    }


    @Test(expected = PersonNotFoundException.class)
    public void updateFirstName_personNotFound() {
        Person person = personDataService.updateFirstName(13L, "Jane");
    }
}
