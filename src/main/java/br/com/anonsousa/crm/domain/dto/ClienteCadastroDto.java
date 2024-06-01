package br.com.anonsousa.crm.domain.dto;


import br.com.anonsousa.crm.domain.model.StatusCliente;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClienteCadastroDto(
        @NotBlank
        @Size(min = 3, max = 80)
        String nome,

        @NotBlank
        @Size(min = 3, max = 80)
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "[\\d\\s()-]+", message = "O campo deve conter apenas dígitos, espaços, parênteses e hífens")
        String telefone,

        EnderecoDto endereco,

        @NotBlank
        @Size(min = 2, max = 80)
        String empresa,

        @NotBlank
        @Pattern(
                regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}",
                message = "CNPJ deve estar no formato XX.XXX.XXX/0001-XX"
        )
        String cnpj,

        @NotBlank
        String cargo,

        @Past(message = "Data de nascimento deve estar no passado")
        LocalDate dataNascimento,

        String notas,
        StatusCliente statusCliente,
        String preferenciaContato
) {
}
