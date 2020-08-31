package br.com.alelo.api.mapper;

import br.com.alelo.api.domain.ClientDomain;
import br.com.alelo.api.dto.ClientDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class ClientMapper {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public ClientDTO mapToDto(ClientDomain clientDomain){
        return ClientDTO.builder()
                .withId(clientDomain.getId())
                .withFirstName(clientDomain.getFirstName())
                .withLastName(clientDomain.getLastName())
                .withBirthdate(sdf.format(clientDomain.getBirthdate()))
                .build();
    }

    public ClientDomain mapToDomain(ClientDTO clientDTO) throws ParseException {
        return ClientDomain.builder()
                .withId(clientDTO.getId())
                .withFirstName(clientDTO.getFirstName())
                .withLastName(clientDTO.getLastName())
                .withBirthdate(sdf.parse(clientDTO.getBirthdate()))
                .build();
    }
}
