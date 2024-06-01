package br.com.anonsousa.crm.controllers;

import br.com.anonsousa.crm.domain.dto.InteracaoCadastroDTO;
import br.com.anonsousa.crm.domain.dto.InteracoesRetornoDTO;
import br.com.anonsousa.crm.domain.model.Interacoes;
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
    private PagedResourcesAssembler<InteracoesRetornoDTO> pagedResourcesAssembler;

    @PostMapping
    public ResponseEntity<InteracoesRetornoDTO> save(@RequestBody @Valid InteracaoCadastroDTO interacaoCadastroDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(interacoesService.save(interacaoCadastroDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InteracoesRetornoDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(interacoesService.findById(id));
    }


    @GetMapping
    public PagedModel<EntityModel<InteracoesRetornoDTO>> findAll(Pageable pageable){
        Page<InteracoesRetornoDTO> interacoesRetornoDTOS = interacoesService.findAllInteracoes(pageable);
        return pagedResourcesAssembler.toModel(interacoesRetornoDTOS);
    }

    @PutMapping
    public ResponseEntity<InteracoesRetornoDTO> update(@RequestBody @Valid InteracoesRetornoDTO interacoesRetornoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(interacoesService.updateInteracao(interacoesRetornoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        interacoesService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
