package main.java.br.com.cryslefundes.service;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.domain.useCases.ClienteUseCase;
import main.java.br.com.cryslefundes.repository.cliente.IClienteRepository;
import main.java.br.com.cryslefundes.service.generic.GenericService;

public class ClienteService extends GenericService<Cliente, Long> implements ClienteUseCase {

    public ClienteService(IClienteRepository clienteRepository) {
        super(clienteRepository);
    }
}
