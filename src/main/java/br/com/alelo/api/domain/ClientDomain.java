package br.com.alelo.api.domain;

import lombok.*;
import lombok.experimental.Tolerate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder(setterPrefix = "with")
@Data
@Table(name="TB_CLIENT")
public class ClientDomain implements Serializable {
    private static final Long serialVersionUid = 1l;

    @Tolerate
    public ClientDomain() {}

    @Tolerate
    public ClientDomain(String firstName, String lastName, Date birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    @NotEmpty(message = "O campo Primeiro Nome é obrigatório")
    private String firstName;

    @Column(name="last_name")
    @NotEmpty(message = "O campo Último Nome é obrigatório")
    private String lastName;

    @NotNull(message = "O campo Data de Nascimento é obrigatório")
    private Date birthdate;
}
