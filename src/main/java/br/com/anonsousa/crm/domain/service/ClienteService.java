package br.com.anonsousa.crm.domain.service;

import br.com.anonsousa.crm.domain.dto.ClienteAtualizarDto;
import br.com.anonsousa.crm.domain.dto.ClienteCadastroDto;
import br.com.anonsousa.crm.domain.dto.ClienteRetornoDto;
import br.com.anonsousa.crm.domain.model.Cliente;
import br.com.anonsousa.crm.domain.model.Endereco;
import br.com.anonsousa.crm.domain.model.StatusCliente;
import br.com.anonsousa.crm.domain.repository.ClienteRepository;
import br.com.anonsousa.crm.infra.exceptions.ClienteNotFoundException;
import br.com.anonsousa.crm.infra.exceptions.InvalidEmailException;
import br.com.anonsousa.crm.infra.exceptions.InvalidPhoneException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteRetornoDto save(ClienteCadastroDto clienteCadastroDTO){
        var validateEmail = clienteRepository.findByEmail(clienteCadastroDTO.email());
        var validatePhone = clienteRepository.findByTelefone(clienteCadastroDTO.telefone());
        if (validateEmail != null){
            throw new InvalidEmailException("Email já existe nos nossos registros!");
        }
        if (validatePhone != null){
            throw new InvalidPhoneException("Telefone já existe nos nossos registros!");
        }


        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteCadastroDTO, cliente);

        if (clienteCadastroDTO.endereco() != null){
            var endereco = new Endereco();
            BeanUtils.copyProperties(clienteCadastroDTO.endereco(), endereco);
            cliente.setEndereco(endereco);
        }


        if (clienteCadastroDTO.statusCliente() == null){
            cliente.setStatusCliente(StatusCliente.POTENCIAL);
        }
        cliente.setDataRegistro(LocalDate.now());

        return new ClienteRetornoDto(clienteRepository.save(cliente));

    }

    public Page<ClienteRetornoDto> findAllClientes(Pageable pageable){
        return clienteRepository.findAll(pageable).map(ClienteRetornoDto::new);
    }

    public ClienteRetornoDto findById(Long id){
        var clienteOp = clienteRepository.findById(id);
        if (clienteOp.isPresent()){
            return new ClienteRetornoDto(clienteOp.get());
        }
        throw new ClienteNotFoundException(String.format("Cliente com o id %d não foi encontrado em nossos registros", id));
    }

    @Transactional
    public ClienteRetornoDto updateCliente(ClienteAtualizarDto clienteAtualizarDTO){
        var clienteOp = clienteRepository.findById(clienteAtualizarDTO.id());
        if (clienteOp.isPresent() && Objects.equals(clienteOp.get().getEmail(), clienteAtualizarDTO.email())){
            var cliente = new Cliente();
            BeanUtils.copyProperties(clienteOp.get(), cliente);
            cliente.setDataRegistro(LocalDate.now());
            return new ClienteRetornoDto(clienteRepository.save(cliente));
        }
        throw new ClienteNotFoundException(String.format("Cliente com o id: %d não foi encontrado em nossos registros", clienteAtualizarDTO.id()));
    }

    @Transactional
    public void deleteById(Long id){
        var clienteOp = clienteRepository.findById(id);
        if (clienteOp.isPresent()){
            clienteRepository.deleteById(id);
        }
        throw new ClienteNotFoundException(String.format("Cliente com o id: %d não encontrado!", id));
    }

}
