package br.com.anonsousa.crm.domain.repository;

import br.com.anonsousa.crm.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByEmail(String email);

    Cliente findByTelefone(String telefone);
}
