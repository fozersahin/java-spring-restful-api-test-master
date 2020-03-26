package uk.co.huntersix.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.co.huntersix.spring.rest.dto.PersonDto;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.PersonDataService;

import javax.xml.ws.Response;

@RestController
public class PersonController {
    private PersonDataService personDataService;

    public PersonController(@Autowired PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping("/person/{lastName}/{firstName}")
    public ResponseEntity<PersonDto> person(@PathVariable(value="lastName") String lastName,
                                 @PathVariable(value="firstName") String firstName) {


        Person person = personDataService.findPerson(lastName, firstName);

        if(person == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PersonDto personDto = new PersonDto(person.getFirstName(),person.getLastName());


        return new ResponseEntity<>(personDto, HttpStatus.OK);
    }
}