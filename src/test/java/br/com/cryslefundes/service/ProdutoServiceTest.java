package test.java.br.com.cryslefundes.service;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.cryslefundes.repository.IProdutoRepository;
import main.java.br.com.cryslefundes.service.IProdutoService;
import main.java.br.com.cryslefundes.service.ProdutoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.java.br.com.cryslefundes.repository.mock.ProdutoRepositoryMock;

import java.math.BigDecimal;
import java.util.Collection;

public class ProdutoServiceTest {
    private IProdutoService produtoService;
    private Produto produto;

    public ProdutoServiceTest() {
        IProdutoRepository repository = new ProdutoRepositoryMock();
        produtoService = new ProdutoService(repository);
    }

    @Before
    public void init() {
        produto = new Produto();
        produto.setCodigo("A1B2C3");
        produto.setNome("Produto 1");
        produto.setDescricao("Produto 1");
        produto.setValor(BigDecimal.TEN);
    }

    @Test
    public void validaPesquisarProduto() {
        Produto produto = this.produtoService.consultar(this.produto.getCodigo());

        Assert.assertNotNull(produto);
    }

    @Test
    public void validaSalvarProduto() throws TipoChaveNaoEncontradaException {
        Boolean valorRetorno = produtoService.cadastrar(produto);

        Assert.assertTrue(valorRetorno);
    }

    @Test
    public void validaExcluirProduto() {
        produtoService.excluir(produto.getCodigo());
    }

    @Test
    public void validaAtualizarProduto() throws TipoChaveNaoEncontradaException {
        produto.setNome("Celular");
        produtoService.alterar(produto);

        Assert.assertEquals("Celular", produto.getNome());
    }

}
