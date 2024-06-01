package br.com.anonsousa.crm.domain.repository;

import br.com.anonsousa.crm.domain.model.Interacoes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InteracoesRepository extends JpaRepository<Interacoes, Long> {

    @Query("SELECT i FROM Interacoes i WHERE i.cliente.id = :clienteId")
    Page<Interacoes> findByClienteId(@Param("clienteId") Long clienteId, Pageable pageable);

}
