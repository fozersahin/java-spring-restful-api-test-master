package uk.co.huntersix.spring.rest.referencedata;

import uk.co.huntersix.spring.rest.model.Person;

import java.util.List;

public interface IPersonDataService {

    List<Person> findPerson(String lastName, String firstName);

    List<Person> findPersonByLastName(String lastName);

}
