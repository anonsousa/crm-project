package br.com.anonsousa.crm.controllers;


import br.com.anonsousa.crm.domain.dto.ClienteCadastroDTO;
import br.com.anonsousa.crm.domain.dto.ClienteRetornoDTO;
import br.com.anonsousa.crm.domain.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cliente")
    public ResponseEntity<ClienteRetornoDTO> save(@RequestBody @Valid ClienteCadastroDTO clienteCadastroDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteCadastroDTO));
    }
}
