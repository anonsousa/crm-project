package br.com.anonsousa.crm.domain.service;

import br.com.anonsousa.crm.domain.dto.ClienteCadastroDTO;
import br.com.anonsousa.crm.domain.dto.ClienteRetornoDTO;
import br.com.anonsousa.crm.domain.model.Cliente;
import br.com.anonsousa.crm.domain.model.Endereco;
import br.com.anonsousa.crm.domain.model.StatusCliente;
import br.com.anonsousa.crm.domain.repository.ClienteRepository;
import br.com.anonsousa.crm.infra.InvalidEmailException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteRetornoDTO save(ClienteCadastroDTO clienteCadastroDTO){
        var validateEmail = clienteRepository.findByEmail(clienteCadastroDTO.email());
        if (validateEmail != null){
            throw new InvalidEmailException("Email já existe nos nossos registros!");
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

        return new ClienteRetornoDTO(clienteRepository.save(cliente));

    }
}
