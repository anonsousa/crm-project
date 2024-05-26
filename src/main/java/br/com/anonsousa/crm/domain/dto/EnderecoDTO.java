package br.com.anonsousa.crm.domain.dto;


import br.com.anonsousa.crm.domain.model.EstadoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(

        @NotBlank
        String rua,

        @NotBlank
        String numero,

        String complemento,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotNull
        EstadoEnum estado,

        @NotBlank
        @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP deve estar no formato XXXXX-XXX")
        String cep

) { }
