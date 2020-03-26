package uk.co.huntersix.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import uk.co.huntersix.spring.rest.dto.PersonDto;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.impl.PersonDataService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private PersonDataService personDataService;

    public PersonController(@Autowired PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping("/person/{lastName}/{firstName}")
    public ResponseEntity<List<PersonDto>> person(@PathVariable(value = "lastName") String lastName,
                                                  @PathVariable(value = "firstName") String firstName) {

        List<Person> personList = personDataService.findPerson(lastName, firstName);

        if (CollectionUtils.isEmpty(personList)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<PersonDto> personDtoList = personList.stream().map(
                s -> new PersonDto(s.getFirstName(), s.getLastName(), s.getId())
        ).collect(Collectors.toList());


        return new ResponseEntity<>(personDtoList, HttpStatus.OK);
    }


    @GetMapping("/person/lastName/{lastName}")
    public ResponseEntity<List<PersonDto>> findPersonByLastName(@PathVariable(value = "lastName") String lastName) {


        List<Person> personList = personDataService.findPersonByLastName(lastName);

        if (CollectionUtils.isEmpty(personList)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<PersonDto> personDtoList = personList.stream().map(
                s -> new PersonDto(s.getFirstName(), s.getLastName(), s.getId())
        ).collect(Collectors.toList());

        return new ResponseEntity<>(personDtoList, HttpStatus.OK);
    }

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public Long findPersonByLastName(@Valid @RequestBody PersonDto personDto) {

        Person person = new Person(personDto.getFirstName(), personDto.getLastName());
        return  personDataService.addPerson(person);
    }

}