package test.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.cryslefundes.repository.ClienteRepository;
import main.java.br.com.cryslefundes.repository.IClienteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class ClienteRepositoryTest {
    private IClienteRepository clienteRepository;
    private Cliente cliente;

    public ClienteRepositoryTest() {
        this.clienteRepository = new ClienteRepository();
    }

    @Before
    public void init() {
        cliente = new Cliente();
        cliente.setCpf("44124124124");
        cliente.setNome("Crystian");
        cliente.setCidade("Rio de janeiro");
        cliente.setEndereco("Rua dos bobos");
        cliente.setEstado("RJ");
        cliente.setTelefone("219312387423");
    }

    @Test
    public void validaPesquisarCliente() {
        Cliente clienteConsultado = clienteRepository.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void validaSalvarCliente() throws TipoChaveNaoEncontradaException {
        Boolean retornoCadastro = clienteRepository.cadastrar(cliente);
        Assert.assertTrue(retornoCadastro);
    }

    @Test
    public void validaExcluirCliente() {
        clienteRepository.excluir(cliente.getCpf());
    }

    @Test
    public void validaAlterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Crystian Lefundes");
        clienteRepository.alterar(cliente);
        Assert.assertEquals("Crystian Lefundes", cliente.getNome());
    }

    @Test
    public void validaBuscarCliente() {
        Collection<Cliente> clientes = clienteRepository.buscarTodos();
        Assert.assertNotNull(clientes);
    }
}