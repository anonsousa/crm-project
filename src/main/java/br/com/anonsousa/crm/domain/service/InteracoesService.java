package br.com.anonsousa.crm.domain.service;

import br.com.anonsousa.crm.domain.dto.InteracaoCadastroDTO;
import br.com.anonsousa.crm.domain.dto.InteracoesRetornoDTO;
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
    public InteracoesRetornoDTO save(InteracaoCadastroDTO interacaoCadastroDTO){

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

            return new InteracoesRetornoDTO(interacoesRepository.save(interacao));

        }
        throw new ClienteNotFoundException("Cliente não encontrado!");
    }

    public InteracoesRetornoDTO findById(Long id){
        var interacaoOp = interacoesRepository.findById(id);
        if (interacaoOp.isPresent()){
            return new InteracoesRetornoDTO(interacaoOp.get());
        }
        throw new ClienteNotFoundException(String.format("Interação com o id: %d não encontrada em nossos registros", id));
    }

    public Page<InteracoesRetornoDTO> findAllInteracoes(Pageable pageable){
        return interacoesRepository.findAll(pageable).map(InteracoesRetornoDTO::new);
    }

    @Transactional
    public InteracoesRetornoDTO updateInteracao(InteracoesRetornoDTO interacoesRetornoDTO){
        var interacaoOp = interacoesRepository.findById(interacoesRetornoDTO.id());
        if (interacaoOp.isPresent()){
            var interacao = new Interacoes();
            BeanUtils.copyProperties(interacoesRetornoDTO, interacao);
            return new InteracoesRetornoDTO(interacoesRepository.save(interacao));
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
