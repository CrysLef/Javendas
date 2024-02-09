package test.java.br.com.cryslefundes.repository.mock;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.cryslefundes.repository.IClienteRepository;

import java.util.Collection;

public class ClienteRepositoryMock implements IClienteRepository {

    @Override
    public Boolean cadastrar(Cliente entidade) throws TipoChaveNaoEncontradaException {
        return true;
    }

    @Override
    public void excluir(String valor) {

    }

    @Override
    public void alterar(Cliente entidade) throws TipoChaveNaoEncontradaException {

    }

    @Override
    public Cliente consultar(String valor) {
        Cliente cliente = new Cliente();
        cliente.setCpf(valor);
        return cliente;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return null;
    }
}
