package br.com.anonsousa.crm.controllers;


import br.com.anonsousa.crm.domain.dto.ClienteAtualizarDTO;
import br.com.anonsousa.crm.domain.dto.ClienteCadastroDTO;
import br.com.anonsousa.crm.domain.dto.ClienteRetornoDTO;
import br.com.anonsousa.crm.domain.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cliente")
    public ResponseEntity<ClienteRetornoDTO> save(@RequestBody @Valid ClienteCadastroDTO clienteCadastroDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteCadastroDTO));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteRetornoDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @GetMapping("/cliente")
    public ResponseEntity<Page<ClienteRetornoDTO>> findAll(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAllClientes(pageable));
    }

    @PutMapping("/cliente")
    public ResponseEntity<ClienteRetornoDTO> update(@RequestBody @Valid ClienteAtualizarDTO clienteAtualizarDTO){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.updateCliente(clienteAtualizarDTO));
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
