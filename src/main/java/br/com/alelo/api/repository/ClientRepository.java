package br.com.alelo.api.repository;

import br.com.alelo.api.domain.ClientDomain;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<ClientDomain, Long> {
}
