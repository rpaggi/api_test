package br.com.alelo.api;

import br.com.alelo.api.domain.ClientDomain;
import br.com.alelo.api.repository.ClientRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void createShouldPersistData() throws ParseException {
        ClientDomain clientDomain = new ClientDomain("Ronaldo Cesar", "Paggi Ribeiro", sdf.parse("1990-08-23"));
        this.clientRepository.save(clientDomain);

        assertThat(clientDomain.getId()).isNotNull();
        assertThat(clientDomain.getFirstName()).isEqualTo("Ronaldo Cesar");
        assertThat(clientDomain.getLastName()).isEqualTo("Paggi Ribeiro");
        assertThat(clientDomain.getBirthdate()).isEqualTo(sdf.parse("1990-08-23"));
    }

    @Test
    public void deleteShouldRemoveData() throws ParseException {
        ClientDomain clientDomain = new ClientDomain("Ronaldo Cesar", "Paggi Ribeiro", sdf.parse("1990-08-23"));
        this.clientRepository.save(clientDomain);
        this.clientRepository.delete(clientDomain);

        assertThat(clientRepository.findById(clientDomain.getId())).isEqualTo(Optional.empty());
    }

    @Test
    public void updateShouldChangeAndPersistData() throws ParseException {
        ClientDomain clientDomain = new ClientDomain("Ronaldo Cesar", "Paggi Ribeiro", sdf.parse("1990-08-23"));

        this.clientRepository.save(clientDomain);
        clientDomain.setLastName("P. Ribeiro");
        this.clientRepository.save(clientDomain);

        clientDomain = this.clientRepository.findById(clientDomain.getId()).get();

        assertThat(clientDomain.getFirstName()).isEqualTo("Ronaldo Cesar");
        assertThat(clientDomain.getLastName()).isEqualTo("P. Ribeiro");
        assertThat(clientDomain.getBirthdate()).isEqualTo(sdf.parse("1990-08-23"));
    }

    @Test
    public void createWhenClientIsEmptyShouldThrownConstraintViolationException(){
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("O campo Primeiro Nome é obrigatório");
        thrown.expectMessage("O campo Último Nome é obrigatório");
        thrown.expectMessage("O campo Data de Nascimento é obrigatório");

        this.clientRepository.save(new ClientDomain());
    }

    @Test
    public void createWhenFirstNameIsNullShouldThrownConstraintViolationException() throws ParseException {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("O campo Primeiro Nome é obrigatório");

        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setLastName("Paggi Ribeiro");
        clientDomain.setBirthdate(sdf.parse("1990-08-23"));

        this.clientRepository.save(clientDomain);
    }

    @Test
    public void createWhenLastNameIsNullShouldThrownConstraintViolationException() throws ParseException {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("O campo Último Nome é obrigatório");

        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setFirstName("Ronaldo Cesar");
        clientDomain.setBirthdate(sdf.parse("1990-08-23"));

        this.clientRepository.save(clientDomain);
    }

    @Test
    public void createWhenBirthdateIsNullShouldThrownConstraintViolationException() throws ParseException {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("O campo Data de Nascimento é obrigatório");

        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setFirstName("Ronaldo Cesar");
        clientDomain.setLastName("Paggi Ribeiro");

        this.clientRepository.save(clientDomain);
    }
}
