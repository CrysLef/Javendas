package test.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.repository.IProdutoRepository;
import main.java.br.com.cryslefundes.repository.ProdutoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class ProdutoRepositoryTest {
    private IProdutoRepository produtoRepository;
    private Produto produto1;
    private Produto produto2;
    private Collection<Produto> produtos = new ArrayList<>();

    public ProdutoRepositoryTest() {
        produtoRepository = new ProdutoRepository();
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

    @Test
    public void validaSalvarProduto() {
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.forEach(p -> {
            Boolean isCadastrado = produtoRepository.cadastrar(p);
            Assert.assertTrue(isCadastrado);
        });
    }

    @Test
    public void validaConsultarProduto() {
        produtos = produtoRepository.buscarTodos();
        produtos.forEach(p -> {
            Produto produtoConsultado = produtoRepository.consultar(p.getCodigo());
            Assert.assertNotNull(produtoConsultado);
        });
    }

    @Test
    public void validaAlterarProduto() {
        produtos = produtoRepository.buscarTodos();
        Produto produto = produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase("smartphone ruim"))
                .toList().get(0);

        produto.setNome("Celular bom");
        produtoRepository.alterar(produto);

        Assert.assertEquals("Celular bom", produto.getNome());
    }

    @Test
    public void validaBuscarTodos() {
        produtos = produtoRepository.buscarTodos();
        Assert.assertNotNull(produtos);
    }

    @Test
    public void validaExcluirProduto() {
        produtos = produtoRepository.buscarTodos();
        produtos.forEach(p -> {
            produtoRepository.excluir(p.getCodigo());
            Produto produtoConsultado = produtoRepository.consultar(p.getCodigo());
            Assert.assertNull(produtoConsultado);
        });
    }
}
