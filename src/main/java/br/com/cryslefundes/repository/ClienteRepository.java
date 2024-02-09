package main.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.repository.generic.GenericRepository;

public class ClienteRepository extends GenericRepository<Cliente, String> implements IClienteRepository{

    public ClienteRepository() {
        super();
    }

    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    public void atualizarDados(Cliente entidade, Cliente entidadeCadastrado) {
        entidadeCadastrado.setCpf(entidade.getCpf());
        entidadeCadastrado.setNome(entidade.getNome());
        entidadeCadastrado.setCidade(entidade.getCidade());
        entidadeCadastrado.setEndereco(entidade.getEndereco());
        entidadeCadastrado.setTelefone(entidade.getTelefone());
    }
}
