package uk.co.huntersix.spring.rest.dto;


import javax.validation.constraints.NotNull;

//Created for response customisation.
public class PersonDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private Long id;

    public PersonDto() {
    }

    public PersonDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public PersonDto(String firstName, String lastName, Long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
