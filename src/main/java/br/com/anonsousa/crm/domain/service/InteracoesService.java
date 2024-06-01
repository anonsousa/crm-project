package br.com.anonsousa.crm.domain.service;

import br.com.anonsousa.crm.domain.dto.InteracaoAtualizarDto;
import br.com.anonsousa.crm.domain.dto.InteracaoCadastroDto;
import br.com.anonsousa.crm.domain.dto.InteracoesRetornoDto;
import br.com.anonsousa.crm.domain.model.Interacoes;
import br.com.anonsousa.crm.domain.repository.ClienteRepository;
import br.com.anonsousa.crm.domain.repository.InteracoesRepository;
import br.com.anonsousa.crm.infra.exceptions.ClienteNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class InteracoesService {
    @Autowired
    private InteracoesRepository interacoesRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TimeValidation timeValidation;

    @Transactional
    public InteracoesRetornoDto save(InteracaoCadastroDto interacaoCadastroDTO){

        var time = LocalDateTime.now();
        timeValidation.validateHourInteraction(time);

        var clienteOp = clienteRepository.findById(interacaoCadastroDTO.clienteId());

        if (clienteOp.isPresent()){

            var interacao = new Interacoes();
            BeanUtils.copyProperties(interacaoCadastroDTO, interacao);
            interacao.setCliente(clienteOp.get());

            if (interacaoCadastroDTO.dataHora() == null){

                interacao.setDataHora(time.withNano(0));
            }

            return new InteracoesRetornoDto(interacoesRepository.save(interacao));

        }
        throw new ClienteNotFoundException("Cliente não encontrado!");
    }

    public InteracoesRetornoDto findById(Long id){
        var interacaoOp = interacoesRepository.findById(id);
        if (interacaoOp.isPresent()){
            return new InteracoesRetornoDto(interacaoOp.get());
        }
        throw new ClienteNotFoundException(String.format("Interação com o id: %d não encontrada em nossos registros", id));
    }

    public Page<InteracoesRetornoDto> findAllInteracoes(Pageable pageable){
        return interacoesRepository.findAll(pageable).map(InteracoesRetornoDto::new);
    }

    public Page<InteracoesRetornoDto> findByIdCliente(Long id, Pageable pageable){
        return interacoesRepository.findByClienteId(id, pageable).map(InteracoesRetornoDto::new);
    }

    @Transactional
    public InteracoesRetornoDto updateInteracao(InteracaoAtualizarDto interacaoAtualizarDTO){
        var interacaoOp = interacoesRepository.findById(interacaoAtualizarDTO.id());

        if (interacaoOp.isPresent()){
            var interacao = new Interacoes();
            var cliente = clienteRepository.findById(interacaoAtualizarDTO.clienteId()).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));

            BeanUtils.copyProperties(interacaoAtualizarDTO, interacao);
            interacao.setCliente(cliente);

            if (interacao.getDataHora() == null){
                interacao.setDataHora(LocalDateTime.now().withNano(0));
            }

            return new InteracoesRetornoDto(interacoesRepository.save(interacao));
        }

        throw new ClienteNotFoundException("Interação não encontrada!");
    }

    @Transactional
    public void deleteById(Long id){
        var interacao = interacoesRepository.findById(id);
        if (interacao.isPresent()){
            interacoesRepository.deleteById(interacao.get().getId());
        }
        throw new ClienteNotFoundException(String.format("Id: d% não encontrado no nosso banco de dados!", id));
    }
}
