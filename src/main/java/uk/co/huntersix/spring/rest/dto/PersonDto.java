package uk.co.huntersix.spring.rest.dto;


//Created for response customisation.
public class PersonDto {

    private String firstName;
    private String lastName;
    private Long id;


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
