package test.java.br.com.cryslefundes.service;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.cryslefundes.repository.IClienteRepository;
import main.java.br.com.cryslefundes.service.ClienteService;
import main.java.br.com.cryslefundes.service.IClienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.java.br.com.cryslefundes.repository.mock.ClienteRepositoryMock;

import java.util.Collection;

public class ClienteServiceTest {
    private IClienteService clienteService;
    private Cliente cliente;

    public ClienteServiceTest() {
        IClienteRepository repository = new ClienteRepositoryMock();
        this.clienteService = new ClienteService(repository);
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
        Cliente clienteConsultado = clienteService.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void validaSalvarCliente() throws TipoChaveNaoEncontradaException {
        Boolean retornoCadastro = clienteService.cadastrar(cliente);
        Assert.assertTrue(retornoCadastro);
    }

    @Test
    public void validaExcluirCliente() {
        clienteService.excluir(cliente.getCpf());
    }

    @Test
    public void validaAlterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Crystian Lefundes");
        clienteService.alterar(cliente);
        Assert.assertEquals("Crystian Lefundes", cliente.getNome());
    }

}
