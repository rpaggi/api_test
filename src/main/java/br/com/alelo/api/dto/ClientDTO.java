package br.com.alelo.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class ClientDTO {
    private String firstName;
    private String lastName;
    private String birthdate;
}
