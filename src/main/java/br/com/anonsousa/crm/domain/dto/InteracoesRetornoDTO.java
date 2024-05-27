package br.com.anonsousa.crm.domain.dto;

import br.com.anonsousa.crm.domain.model.Cliente;
import br.com.anonsousa.crm.domain.model.Interacoes;
import br.com.anonsousa.crm.domain.model.TipoInteracao;

import java.time.LocalDateTime;

public record InteracoesRetornoDTO(

        Long id,
        TipoInteracao tipo,
        String descricao,
        String assunto,
        LocalDateTime dataHora,
        String participantes,
        Cliente cliente
) {
    public InteracoesRetornoDTO(Interacoes interacoes){
        this(
                interacoes.getId(),
                interacoes.getTipo(),
                interacoes.getDescricao(),
                interacoes.getAssunto(),
                interacoes.getDataHora(),
                interacoes.getParticipantes(),
                interacoes.getCliente()
        );
    }
}
