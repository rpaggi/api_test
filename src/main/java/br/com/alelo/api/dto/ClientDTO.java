package br.com.alelo.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class ClientDTO {
    private Long id;

    @ApiModelProperty(example = "Ronaldo Cesar")
    private String firstName;
    @ApiModelProperty(example = "Paggi Ribeiro")
    private String lastName;
    @ApiModelProperty(example = "23/08/1990")
    private String birthdate;

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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
