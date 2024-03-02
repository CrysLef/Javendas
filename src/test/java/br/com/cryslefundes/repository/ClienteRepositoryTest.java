package test.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.repository.cliente.ClienteRepository;
import main.java.br.com.cryslefundes.repository.cliente.IClienteRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Random;

public class ClienteRepositoryTest {
    private IClienteRepository repository;
    private Random random;

    public ClienteRepositoryTest() {
        this.repository = new ClienteRepository();
        this.random = new Random();
    }

    @After
    public void endTest() {
        Collection<Cliente> clientes = this.repository.buscarTodos();
        clientes.forEach(this.repository::excluir);
    }

    private Cliente createCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Crystian Lefundes");
        cliente.setCpf(random.nextLong());
        cliente.setTelefone("11964134264");
        cliente.setEmail("crystian_lefundes@email.com");
        cliente.setEndereco("Rua dos bobos");
        cliente.setCidade("Sao paulo");
        cliente.setEstado("SP");
        return cliente;
    }

    @Test
    public void validaCadastrar() {
        Cliente cliente = createCliente();
        Cliente clienteCadastrado = this.repository.cadastrar(cliente);
        Assert.assertNotNull(clienteCadastrado);
    }

    @Test
    public void validaConsultar() {
        Cliente cliente = createCliente();
        Cliente clienteCadastrado = this.repository.cadastrar(cliente);
        Assert.assertNotNull(clienteCadastrado);

        Cliente clienteConsultado = this.repository.consultar(clienteCadastrado.getId());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void validaAlterar() {
        Cliente cliente = createCliente();
        Cliente clienteCadastrado = this.repository.cadastrar(cliente);
        Assert.assertNotNull(clienteCadastrado);

        clienteCadastrado.setNome("Ana Maria");
        clienteCadastrado.setEmail("ana_maria@email.com");

        Cliente clienteAlterado = this.repository.alterar(clienteCadastrado);
        Assert.assertNotNull(clienteAlterado);

        Cliente clienteAlteradoConsultado = this.repository.consultar(clienteAlterado.getId());
        Assert.assertNotNull(clienteAlteradoConsultado);

        Assert.assertEquals("Ana Maria", clienteAlteradoConsultado.getNome());
        Assert.assertEquals("ana_maria@email.com", clienteAlteradoConsultado.getEmail());
    }

    @Test
    public void validaBuscarTodos() {
        Cliente cliente = createCliente();
        Cliente clienteCadastrado = this.repository.cadastrar(cliente);
        Assert.assertNotNull(clienteCadastrado);

        Cliente cliente2 = createCliente();
        Cliente cliente2Cadastrado = this.repository.cadastrar(cliente2);
        Assert.assertNotNull(cliente2Cadastrado);

        Collection<Cliente> clientes = this.repository.buscarTodos();
        Assert.assertNotNull(clientes);
        Assert.assertEquals(2, clientes.size());
    }

    @Test
    public void validaExcluir() {
        Cliente cliente = createCliente();
        Cliente clienteCadastrado = this.repository.cadastrar(cliente);
        Assert.assertNotNull(clienteCadastrado);

        Collection<Cliente> clientes = this.repository.buscarTodos();
        clientes.forEach(this.repository::excluir);
    }
}
