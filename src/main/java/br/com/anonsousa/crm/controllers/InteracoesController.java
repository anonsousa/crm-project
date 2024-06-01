package br.com.anonsousa.crm.controllers;

import br.com.anonsousa.crm.domain.dto.InteracaoAtualizarDto;
import br.com.anonsousa.crm.domain.dto.InteracaoCadastroDto;
import br.com.anonsousa.crm.domain.dto.InteracoesRetornoDto;
import br.com.anonsousa.crm.domain.service.InteracoesService;
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
@RequestMapping("/api/interacoes")
public class InteracoesController {

    @Autowired
    private InteracoesService interacoesService;

    @Autowired
    private PagedResourcesAssembler<InteracoesRetornoDto> pagedResourcesAssembler;

    @PostMapping
    public ResponseEntity<InteracoesRetornoDto> save(@RequestBody @Valid InteracaoCadastroDto interacaoCadastroDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(interacoesService.save(interacaoCadastroDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InteracoesRetornoDto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(interacoesService.findById(id));
    }


    @GetMapping
    public PagedModel<EntityModel<InteracoesRetornoDto>> findAll(Pageable pageable){
        Page<InteracoesRetornoDto> interacoesRetornoDTOS = interacoesService.findAllInteracoes(pageable);
        return pagedResourcesAssembler.toModel(interacoesRetornoDTOS);
    }

    @GetMapping("/cliente/{id}")
    public PagedModel<EntityModel<InteracoesRetornoDto>> findByClienteId(@PathVariable Long id, Pageable pageable){
        Page<InteracoesRetornoDto> interacoesRetornoDTOS = interacoesService.findByIdCliente(id, pageable);
        return pagedResourcesAssembler.toModel(interacoesRetornoDTOS);
    }

    @PutMapping
    public ResponseEntity<InteracoesRetornoDto> update(@RequestBody @Valid InteracaoAtualizarDto interacaoAtualizarDTO){
        return ResponseEntity.status(HttpStatus.OK).body(interacoesService.updateInteracao(interacaoAtualizarDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        interacoesService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
