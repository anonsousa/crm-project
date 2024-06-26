package br.com.anonsousa.crm.domain.dto;

import br.com.anonsousa.crm.domain.model.Cliente;
import br.com.anonsousa.crm.domain.model.Endereco;
import br.com.anonsousa.crm.domain.model.StatusCliente;

import java.time.LocalDate;

public record ClienteRetornoDTO(

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
    public ClienteRetornoDTO(Cliente cliente){
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
