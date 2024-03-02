package main.java.br.com.cryslefundes.repository.cliente;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.repository.generic.GenericRepository;

public class ClienteRepository extends GenericRepository<Cliente, Long> implements IClienteRepository{

    public ClienteRepository() {
        super(Cliente.class);
    }
}
