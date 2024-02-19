package test.java.br.com.cryslefundes.service;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.repository.IClienteRepository;
import main.java.br.com.cryslefundes.service.ClienteService;
import main.java.br.com.cryslefundes.service.IClienteService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.java.br.com.cryslefundes.repository.mock.ClienteRepositoryMock;

import java.util.ArrayList;
import java.util.Collection;


public class ClienteServiceTest {
    private IClienteService clienteService;
    private Cliente cliente1;
    private Cliente cliente2;
    private Collection<Cliente> clientes = new ArrayList<>();

    public ClienteServiceTest() {
        IClienteRepository repository = new ClienteRepositoryMock();
        this.clienteService = new ClienteService(repository);
    }

    @Before
    public void init() {
        // cliente 1:
        cliente1 = new Cliente();
        cliente1.setCpf(44124124124L);
        cliente1.setNome("Crystian");
        cliente1.setCidade("Rio de janeiro");
        cliente1.setEndereco("Rua dos bobos, 0");
        cliente1.setEstado("RJ");
        cliente1.setTelefone("+55 (21) 92312-3423");
        cliente1.setEmail("funcionario1@admin.com");

        // cliente 2:
        cliente2 = new Cliente();
        cliente2.setCpf(31178964156L);
        cliente2.setNome("Juan");
        cliente2.setCidade("Belo Horizonte");
        cliente2.setEndereco("Avenida Inexistente, 87");
        cliente2.setEstado("MG");
        cliente2.setTelefone("+55 (31) 98491-2042");
        cliente2.setEmail("funcionario2@admin.com");
    }

    @After
    public void truncateClientes() {
        clientes = clienteService.buscarTodos();
        if (clientes != null) {
            clientes.forEach(c -> clienteService.excluir(c.getCpf()));
        }
    }
    
    @Test
    public void validaSalvarCliente() {
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.forEach(c -> {
            Boolean isCadastrado = clienteService.cadastrar(c);
            Assert.assertTrue(isCadastrado);
        });
    }

    @Test
    public void validaConsultarCliente() {
        clientes = clienteService.buscarTodos();
        clientes.forEach(c -> {
            Cliente clienteConsultado = clienteService.consultar(c.getCpf());
            Assert.assertNotNull(clienteConsultado);
        });
    }

    @Test
    public void validaAlterarCliente() {
        cliente1.setNome("Crystian Lefundes");
        clienteService.alterar(cliente1);
        Assert.assertEquals("Crystian Lefundes", cliente1.getNome());
    }

    @Test
    public void validaBuscarTodosClientes() {
        clientes = clienteService.buscarTodos();
        Assert.assertNotNull(clientes);
    }

    @Test
    public void validaExcluirCliente() {
        clientes = clienteService.buscarTodos();
        clientes.forEach(c -> {
            clienteService.excluir(c.getCpf());
            Cliente clienteConsultado = clienteService.consultar(c.getCpf());
        });
    }

}
