package test.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.repository.ClienteRepository;
import main.java.br.com.cryslefundes.repository.IClienteRepository;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collection;

public class ClienteRepositoryTest {
    private IClienteRepository clienteRepository;
    private Cliente cliente1;
    private Cliente cliente2;
    private Collection<Cliente> clientes = new ArrayList<>();

    public ClienteRepositoryTest() {
        this.clienteRepository = new ClienteRepository();
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

    @Test
    public void validaSalvarCliente() {
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.forEach(c -> {
            Boolean isCadastrado = clienteRepository.cadastrar(c);
            Assert.assertTrue(isCadastrado);
        });

    }

    @Test
    public void validaConsultarCliente() {
        clientes = clienteRepository.buscarTodos();
        clientes.forEach(c -> {
            Cliente clienteConsultado = clienteRepository.consultar(c.getCpf());
            Assert.assertNotNull(clienteConsultado);
        });
    }

    @Test
    public void validaAlterarCliente() {
        clientes = clienteRepository.buscarTodos();
        Cliente cliente = clientes.stream()
                .filter(c -> c.getNome().equalsIgnoreCase("crystian"))
                .toList().get(0);

        cliente.setNome("Crystian Lefundes");
        clienteRepository.alterar(cliente);
        Assert.assertEquals("Crystian Lefundes", cliente.getNome());
    }

    @Test
    public void validaBuscarCliente() {
        clientes = clienteRepository.buscarTodos();
        Assert.assertNotNull(clientes);
    }

    @Test
    public void validaExcluirCliente() {
        clientes = clienteRepository.buscarTodos();
        clientes.forEach(c -> {
            clienteRepository.excluir(c.getCpf());
            Cliente clienteConsultado = clienteRepository.consultar(c.getCpf());
            Assert.assertNull(clienteConsultado);
        });
    }
}