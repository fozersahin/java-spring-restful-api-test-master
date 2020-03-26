package uk.co.huntersix.spring.rest.referencedata.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import uk.co.huntersix.spring.rest.exception.PersonNotFoundException;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.IPersonDataService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonDataService implements IPersonDataService {

    private static List<Person> PERSON_DATA = new LinkedList<>(Arrays.asList(
            new Person("Mary", "Smith"),
            new Person("Brian", "Archer"),
            new Person("Collin", "Brown"),
            new Person("Brian", "Smith"))
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

    @Override
    public Long addPerson(Person person) {
        PERSON_DATA.add(person);
        return person.getId();
    }


    public Person updateFirstName(Long id, String firstName) {


        List<Person> personList = PERSON_DATA.stream()
                .filter(s -> s.getId().equals(id))
                .collect(Collectors.toList());

        if(CollectionUtils.isEmpty(personList)) {
            throw new PersonNotFoundException();
        }

        PERSON_DATA.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .ifPresent(t -> t.setFirstName(firstName));

        return PERSON_DATA.stream()
                .filter(p -> p.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }
}
