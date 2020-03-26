package uk.co.huntersix.spring.rest.referencedata.impl;

import org.springframework.stereotype.Service;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.IPersonDataService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonDataService implements IPersonDataService {

    private static final List<Person> PERSON_DATA = Arrays.asList(
            new Person("Mary", "Smith"),
            new Person("Brian", "Archer"),
            new Person("Collin", "Brown"),
            new Person("Brian", "Smith")
    );

    @Override
    public List<Person> findPerson(String lastName, String firstName) {
        return PERSON_DATA.stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(firstName)
                        && p.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findPersonByLastName(String lastName) {
        return PERSON_DATA.stream()
                .filter(p -> p.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }
}
