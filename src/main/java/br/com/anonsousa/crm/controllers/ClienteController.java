package br.com.anonsousa.crm.controllers;


import br.com.anonsousa.crm.domain.dto.ClienteAtualizarDto;
import br.com.anonsousa.crm.domain.dto.ClienteCadastroDto;
import br.com.anonsousa.crm.domain.dto.ClienteRetornoDto;
import br.com.anonsousa.crm.domain.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PagedResourcesAssembler<ClienteRetornoDto> pagedResourcesAssembler;

    @PostMapping("/cliente")
    public ResponseEntity<ClienteRetornoDto> save(@RequestBody @Valid ClienteCadastroDto clienteCadastroDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteCadastroDTO));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteRetornoDto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @GetMapping("/cliente")
    public PagedModel<EntityModel<ClienteRetornoDto>> findAll(Pageable pageable){
        Page<ClienteRetornoDto> page = clienteService.findAllClientes(pageable);
        return pagedResourcesAssembler.toModel(page);
    }

    @PutMapping("/cliente")
    public ResponseEntity<ClienteRetornoDto> update(@RequestBody @Valid ClienteAtualizarDto clienteAtualizarDTO){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.updateCliente(clienteAtualizarDTO));
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
