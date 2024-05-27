package br.com.anonsousa.crm.domain.dto;

import br.com.anonsousa.crm.domain.model.TipoInteracao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InteracaoCadastroDTO(

        @NotNull
        TipoInteracao tipo,

        @NotBlank
        String descricao,

        @NotBlank
        String assunto,

        LocalDateTime dataHora,

        String participantes,

        @NotNull
        Long clienteId
) {
}
