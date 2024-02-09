package main.java.br.com.cryslefundes.service;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.repository.IClienteRepository;
import main.java.br.com.cryslefundes.service.generic.GenericService;

public class ClienteService extends GenericService<Cliente, String> implements IClienteService {

    public ClienteService(IClienteRepository ClienteRepository) {
        super(ClienteRepository);
    }
}
