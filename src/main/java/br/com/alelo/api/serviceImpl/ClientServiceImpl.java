package br.com.alelo.api.serviceImpl;

import br.com.alelo.api.domain.ClientDomain;
import br.com.alelo.api.dto.ClientDTO;
import br.com.alelo.api.mapper.ClientMapper;
import br.com.alelo.api.repository.ClientRepository;
import br.com.alelo.api.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService<ClientRepository, ClientDTO> {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientDTO> findAll() {
        List<ClientDomain> clients = (List<ClientDomain>)clientRepository.findAll();
        if(!clients.isEmpty()){
            return clients.stream().map(client->clientMapper.mapToDto(client)).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public ClientDTO save(ClientDTO dto) throws ParseException {
        ClientDomain clientDomain = clientMapper.mapToDomain(dto);
        return clientMapper.mapToDto(clientRepository.save(clientDomain));
    }

    @Override
    public ClientDTO update(ClientDTO dto) throws ParseException {
        Optional<ClientDomain> client = clientRepository.findById(dto.getId());

        if(client.equals(Optional.empty())){
            return null;
        }

        ClientDomain clientDomain = client.get();

        clientDomain.setFirstName(dto.getFirstName());
        clientDomain.setLastName(dto.getLastName());
        clientDomain.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getBirthdate()));

        return clientMapper.mapToDto(clientRepository.save(clientDomain));
    }

    @Override
    public ClientDTO findById(Long id) {
        Optional<ClientDomain> clientDomain = clientRepository.findById(id);
        if(clientDomain.equals(Optional.empty())){
            return null;
        }
        return clientMapper.mapToDto(clientDomain.get());
    }

    @Override
    public void delete(Long id) {
        try {
            Optional<ClientDomain> clientDomain = clientRepository.findById(id);
            if(!clientDomain.equals(Optional.empty())){
                clientRepository.deleteById(id);
            }
        }catch(EmptyResultDataAccessException e){}
    }
}