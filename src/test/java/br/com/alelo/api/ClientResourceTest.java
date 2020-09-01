package br.com.alelo.api;

import br.com.alelo.api.domain.ClientDomain;
import br.com.alelo.api.dto.ClientDTO;
import br.com.alelo.api.mapper.ClientMapper;
import br.com.alelo.api.repository.ClientRepository;
import br.com.alelo.api.resource.ClientResource;
import br.com.alelo.api.serviceImpl.ClientServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SpringRunner.class)
@WebFluxTest({ClientResource.class})
@Import({ ClientServiceImpl.class, ClientMapper.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureDataJpa
public class ClientResourceTest {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private WebTestClient webClient;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void listAllClients() throws ParseException {
        ClientDomain clientDomain = new ClientDomain("Ronaldo Cesar", "Paggi Ribeiro", sdf.parse("1990-08-23"));
        this.clientRepository.save(clientDomain);

        webClient.get()
                .uri("/client")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ArrayList.class)
                .value(client -> client.size(), equalTo(1));
    }

    @Test
    public void getClient() throws ParseException {
        ClientDomain clientDomain = new ClientDomain("Ronaldo Cesar", "Paggi Ribeiro", sdf.parse("1990-08-23"));
        this.clientRepository.save(clientDomain);

        webClient.get()
                .uri("/client/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ClientDTO.class)
                .value(client -> client, notNullValue());
    }

    @Test
    public void createClient() {
        String client = "{\"firstName\":\"Ronaldo Cesar\", \"lastName\":\"Paggi Ribeiro\",\"birthdate\":\"23/08/1990\"}";

        webClient.post()
                .uri("/client")
                .header("Content-Type", "application/json")
                .body(Mono.just(client), String.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void updateClient() throws ParseException {
        ClientDomain clientDomain = new ClientDomain("Ronaldo Cesar", "Paggi Ribeiro", sdf.parse("1990-08-23"));
        this.clientRepository.save(clientDomain);

        String client = "{\"id\":\"1\", \"firstName\":\"Ronaldo C.\", \"lastName\":\"P. Ribeiro\",\"birthdate\":\"23/08/1990\"}";

        webClient.put()
                .uri("/client")
                .header("Content-Type", "application/json")
                .body(Mono.just(client), String.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void deleteClient() throws ParseException {
        ClientDomain clientDomain = new ClientDomain("Ronaldo Cesar", "Paggi Ribeiro", sdf.parse("1990-08-23"));
        this.clientRepository.save(clientDomain);

        webClient.delete()
                .uri("/client/1")
                .exchange()
                .expectStatus()
                .isOk();
    }

}
