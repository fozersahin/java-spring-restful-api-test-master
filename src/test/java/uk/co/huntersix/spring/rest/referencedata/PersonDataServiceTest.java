package uk.co.huntersix.spring.rest.referencedata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
}
