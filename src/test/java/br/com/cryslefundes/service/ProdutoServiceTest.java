package test.java.br.com.cryslefundes.service;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.repository.IProdutoRepository;
import main.java.br.com.cryslefundes.service.IProdutoService;
import main.java.br.com.cryslefundes.service.ProdutoService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.java.br.com.cryslefundes.repository.mock.ProdutoRepositoryMock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class ProdutoServiceTest {
    private IProdutoService produtoService;
    private Produto produto1;
    private Produto produto2;
    private Collection<Produto> produtos = new ArrayList<>();

    public ProdutoServiceTest() {
        IProdutoRepository repository = new ProdutoRepositoryMock();
        produtoService = new ProdutoService(repository);
    }

    @Before
    public void init() {
        // produto 1:
        produto1 = new Produto();
        produto1.setCodigo(418263L);
        produto1.setNome("Smartphone ruim");
        produto1.setDescricao("Um celular horrível por preço de banana");
        produto1.setValor(BigDecimal.TEN);
        produto1.setEstoque(42);

        // produto 2:
        produto2 = new Produto();
        produto2.setCodigo(529374L);
        produto2.setNome("Notebook caro");
        produto2.setDescricao("Um notebook muito caro que não vale o preço");
        produto2.setValor(BigDecimal.TEN);
        produto2.setEstoque(23);
    }

    @After
    public void truncateProdutos() {
        produtos = produtoService.buscarTodos();
        if (produtos != null) {
            produtos.forEach(p -> produtoService.excluir(p.getCodigo()));
        }
    }

    @Test
    public void validaSalvarProduto() {
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.forEach(p -> {
            Boolean isCadastrado = produtoService.cadastrar(p);
            Assert.assertTrue(isCadastrado);
        });
    }

    @Test
    public void validaConsultarProduto() {
        produtos = produtoService.buscarTodos();
        produtos.forEach(p -> {
            Produto produtoConsultado = produtoService.consultar(p.getCodigo());
            Assert.assertNotNull(produtoConsultado);
        });
    }

    @Test
    public void validaAlterarProduto() {
        produto1.setNome("Celular bom");
        produtoService.alterar(produto1);

        Assert.assertEquals("Celular bom", produto1.getNome());
    }

    @Test
    public void validaBuscarTodosClientes() {
        produtos = produtoService.buscarTodos();
        Assert.assertNotNull(produtos);
    }

    @Test
    public void validaExcluirProduto() {
        produtos = produtoService.buscarTodos();
        produtos.forEach(p -> {
            produtoService.excluir(p.getCodigo());
            Produto produtoConsultado = produtoService.consultar(p.getCodigo());
        });
    }

}
