package test.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.cryslefundes.repository.IProdutoRepository;
import main.java.br.com.cryslefundes.repository.ProdutoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

public class ProdutoRepositoryTest {
    private IProdutoRepository produtoRepository;
    private Produto produto;

    public ProdutoRepositoryTest() {
        produtoRepository = new ProdutoRepository();
    }

    @Before
    public void init() {
        produto = new Produto();
        produto.setCodigo(418263L);
        produto.setNome("Produto 1");
        produto.setDescricao("Produto 1");
        produto.setValor(BigDecimal.TEN);
    }

    @Test
    public void validaPesquisarProduto() {
        Produto produto = this.produtoRepository.consultar(this.produto.getCodigo());

        Assert.assertNotNull(produto);
    }

    @Test
    public void validaSalvarProduto() throws TipoChaveNaoEncontradaException {
        Boolean valorRetorno = produtoRepository.cadastrar(produto);

        Assert.assertTrue(valorRetorno);
    }

    @Test
    public void validaExcluirProduto() {
        produtoRepository.excluir(produto.getCodigo());
    }

    @Test
    public void validaAtualizarProduto() throws TipoChaveNaoEncontradaException {
        produto.setNome("Celular");
        produtoRepository.alterar(produto);

        Assert.assertEquals("Celular", produto.getNome());
    }

    @Test
    public void validaBuscarTodos() {
        Collection<Produto> produtos = produtoRepository.buscarTodos();
        Assert.assertNotNull(produtos);
    }
}
