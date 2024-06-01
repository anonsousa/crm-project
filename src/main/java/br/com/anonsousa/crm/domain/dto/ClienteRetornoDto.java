package br.com.anonsousa.crm.domain.dto;

import br.com.anonsousa.crm.domain.model.Cliente;
import br.com.anonsousa.crm.domain.model.Endereco;
import br.com.anonsousa.crm.domain.model.StatusCliente;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

@Relation(collectionRelation = "clientes")
public record ClienteRetornoDto(

        Long id,
        String nome,
        String email,
        String telefone,
        Endereco endereco,
        String empresa,
        String cnpj,
        String cargo,
        LocalDate dataNascimento,
        LocalDate dataRegistro,
        String notas,
        StatusCliente statusCliente,
        String preferenciaContato
) {
    public ClienteRetornoDto(Cliente cliente){
        this (
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getEmpresa(),
                cliente.getCnpj(),
                cliente.getCargo(),
                cliente.getDataNascimento(),
                cliente.getDataRegistro(),
                cliente.getNotas(),
                cliente.getStatusCliente(),
                cliente.getPreferenciaContato()
        );
    }
}
