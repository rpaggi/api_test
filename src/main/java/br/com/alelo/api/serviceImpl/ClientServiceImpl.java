package br.com.alelo.api.serviceImpl;

import br.com.alelo.api.domain.ClientDomain;
import br.com.alelo.api.dto.ClientDTO;
import br.com.alelo.api.mapper.ClientMapper;
import br.com.alelo.api.repository.ClientRepository;
import br.com.alelo.api.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    public ClientDTO update(ClientDTO filter) {
        return null;
    }

    @Override
    public ClientDTO findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}