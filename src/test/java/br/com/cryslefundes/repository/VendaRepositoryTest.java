package test.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.domain.ProdutoQuantidade;
import main.java.br.com.cryslefundes.domain.Venda;
import main.java.br.com.cryslefundes.domain.enums.StatusVenda;
import main.java.br.com.cryslefundes.repository.cliente.ClienteRepository;
import main.java.br.com.cryslefundes.repository.cliente.IClienteRepository;
import main.java.br.com.cryslefundes.repository.produto.IProdutoRepository;
import main.java.br.com.cryslefundes.repository.produto.ProdutoRepository;
import main.java.br.com.cryslefundes.repository.produtoQuantidade.IProdutoQuantidadeRepository;
import main.java.br.com.cryslefundes.repository.produtoQuantidade.ProdutoQuantidadeRepository;
import main.java.br.com.cryslefundes.repository.venda.IVendaRepository;
import main.java.br.com.cryslefundes.repository.venda.VendaRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.Random;

public class VendaRepositoryTest {
    private Random random;
    private IClienteRepository clienteRepository;
    private Cliente cliente;
    private IProdutoRepository produtoRepository;
    private Produto produto;
    private IVendaRepository repository;

    public VendaRepositoryTest() {
        this.random = new Random();
        this.clienteRepository = new ClienteRepository();
        this.produtoRepository = new ProdutoRepository();
        this.repository = new VendaRepository();
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
        this.clienteRepository.cadastrar(cliente);
        return cliente;
    }

    private Produto createProduto() {
        Produto produto = new Produto();
        produto.setNome("Celular");
        produto.setCodigo(random.nextLong());
        produto.setDescricao("Um celular muito bom");
        produto.setEstoque(10);
        produto.setValor(BigDecimal.TEN);
        this.produtoRepository.cadastrar(produto);
        return produto;
    }

    private Venda createVenda() {
        Venda venda = new Venda();
        venda.setCodigo(random.nextLong());
        venda.setStatusVenda(StatusVenda.INICIADA);
        venda.setDataVenda(Instant.now());
        venda.setCliente(this.cliente);
        venda.adicionarProduto(this.produto, 3);
        return venda;
    }

    @Before
    public void setUp() {
        this.cliente = createCliente();
        this.produto = createProduto();
    }

    @After
    public void endTest() {
        Collection<Venda> vendas = this.repository.buscarTodos();
        vendas.forEach(this.repository::excluir);

        Collection<Produto> produtos = this.produtoRepository.buscarTodos();
        produtos.forEach(this.produtoRepository::excluir);

        clienteRepository.excluir(this.cliente);
    }

    @Test
    public void validaCadastrar() {
        Venda venda = createVenda();
        Venda vendaCadastrada = this.repository.cadastrar(venda);
        Assert.assertNotNull(vendaCadastrada);
        Assert.assertEquals(StatusVenda.INICIADA, vendaCadastrada.getStatusVenda());
        Assert.assertEquals(BigDecimal.valueOf(30), vendaCadastrada.getValorTotal());
    }

    @Test
    public void validaConsultar() {
        Venda venda = createVenda();
        Venda vendaCadastrada = this.repository.cadastrar(venda);
        Assert.assertNotNull(vendaCadastrada);

        Venda vendaConsultada = this.repository.consultaLazy(vendaCadastrada.getId());
        Assert.assertNotNull(vendaConsultada);


        Assert.assertEquals(StatusVenda.INICIADA, vendaCadastrada.getStatusVenda());
    }

    @Test
    public void validaAlterar() {
        Venda venda = createVenda();
        Venda vendaCadastrada = this.repository.cadastrar(venda);
        Assert.assertNotNull(vendaCadastrada);

        venda.setValorTotal(BigDecimal.valueOf(40));

        Venda vendaAlterada = this.repository.alterar(venda);
        Assert.assertNotNull(vendaAlterada);

        Venda vendaConsultadaEAlterada = this.repository.consultaLazy(vendaCadastrada.getId());
        Assert.assertNotNull(vendaConsultadaEAlterada);

        Assert.assertNotEquals(BigDecimal.valueOf(40), vendaConsultadaEAlterada.getValorTotal());
    }

    @Test
    public void validaBuscarTodos() {
        Venda venda = createVenda();
        Venda vendaCadastrada = this.repository.cadastrar(venda);
        Assert.assertNotNull(vendaCadastrada);

        Venda venda2 = createVenda();
        Venda venda2Cadastrada = this.repository.cadastrar(venda2);
        Assert.assertNotNull(venda2Cadastrada);

        Collection<Venda> vendas = this.repository.buscarTodos();
        Assert.assertNotNull(vendas);
    }

    @Test
    public void validaExcluir() {
        Venda venda = createVenda();
        Venda vendaCadastrada = this.repository.cadastrar(venda);
        Assert.assertNotNull(vendaCadastrada);

        Collection<Venda> vendas = this.repository.buscarTodos();
        Assert.assertNotNull(vendas);

        vendas.forEach(this.repository::excluir);
    }

    @Test
    public void validaFinalizarVenda() {
        Venda venda = createVenda();
        Venda vendaCadastrada = this.repository.cadastrar(venda);
        Assert.assertNotNull(vendaCadastrada);

        venda.setStatusVenda(StatusVenda.CONCLUIDA);

        Venda vendaAlterada = this.repository.finalizarVenda(venda);
        Assert.assertNotNull(vendaAlterada);

        Venda vendaConsultadaEAlterada = this.repository.consultaLazy(vendaCadastrada.getId());
        Assert.assertNotNull(vendaConsultadaEAlterada);

        Assert.assertNotEquals(StatusVenda.INICIADA, vendaConsultadaEAlterada.getStatusVenda());
    }

    @Test
    public void validaCancelarVenda() {
        Venda venda = createVenda();
        Venda vendaCadastrada = this.repository.cadastrar(venda);
        Assert.assertNotNull(vendaCadastrada);

        venda.setStatusVenda(StatusVenda.CANCELADA);

        Venda vendaAlterada = this.repository.cancelarVenda(venda);
        Assert.assertNotNull(vendaAlterada);

        Venda vendaConsultadaEAlterada = this.repository.consultaLazy(vendaCadastrada.getId());
        Assert.assertNotNull(vendaConsultadaEAlterada);

        Assert.assertNotEquals(StatusVenda.INICIADA, vendaConsultadaEAlterada.getStatusVenda());
    }
}
