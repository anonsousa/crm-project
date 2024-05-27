package br.com.anonsousa.crm.domain.repository;

import br.com.anonsousa.crm.domain.model.Interacoes;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteracoesRepository extends JpaRepository<Interacoes, Long> {

}
