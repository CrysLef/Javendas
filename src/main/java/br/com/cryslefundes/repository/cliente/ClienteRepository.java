package main.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.repository.generic.GenericRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteRepository extends GenericRepository<Cliente, Long> implements IClienteRepository{

    public ClienteRepository() {
        super(Cliente.class);
    }
}
