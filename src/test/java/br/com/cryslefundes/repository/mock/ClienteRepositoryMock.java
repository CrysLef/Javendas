package test.java.br.com.cryslefundes.repository.mock;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.repository.IClienteRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ClienteRepositoryMock implements IClienteRepository {

    Cliente cliente1 = new Cliente();
    Cliente cliente2 = new Cliente();

    @Override
    public Boolean cadastrar(Cliente entidade) {
        return true;
    }

    @Override
    public void excluir(Long valor) {

    }

    @Override
    public void alterar(Cliente entidade) {

    }

    @Override
    public Cliente consultar(Long valor) {

        if (cliente1.getCpf().equals(valor)) {
            return cliente1;
        } else if (cliente2.getCpf().equals(valor)) {
            return cliente2;
        }
        return null;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        Collection<Cliente> clientes = new ArrayList<>();

        if (cliente1 != null && cliente2 != null) {
            cliente1.setCpf(34123L);
            cliente2.setCpf(43151L);
            clientes.add(cliente1);
            clientes.add(cliente2);
            return clientes;
        }
        return null;
    }
}
