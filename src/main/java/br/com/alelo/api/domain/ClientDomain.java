package br.com.alelo.api.domain;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder(setterPrefix = "with")
@Data
@Table(name="TB_CLIENT")
public class ClientDomain implements Serializable {
    private static final Long serialVersionUid = 1l;

    @Tolerate
    ClientDomain() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private Date birthdate;
}
