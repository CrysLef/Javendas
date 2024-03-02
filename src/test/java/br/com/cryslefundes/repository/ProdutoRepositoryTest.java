package test.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.repository.produto.IProdutoRepository;
import main.java.br.com.cryslefundes.repository.produto.ProdutoRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Random;

public class ProdutoRepositoryTest {
    private IProdutoRepository repository;
    private Random random;

    public ProdutoRepositoryTest() {
        this.repository = new ProdutoRepository();
        this.random = new Random();
    }

    @After
    public void endTest() {
        Collection<Produto> produtos = this.repository.buscarTodos();
        produtos.forEach(this.repository::excluir);
    }

    private Produto createProduto() {
        Produto produto = new Produto();
        produto.setNome("Celular");
        produto.setCodigo(random.nextLong());
        produto.setDescricao("Um celular muito bom");
        produto.setEstoque(10);
        produto.setValor(BigDecimal.valueOf(5999));
        return produto;
    }

    @Test
    public void validaCadastrar() {
        Produto produto = createProduto();
        Produto produtoCadastrado = this.repository.cadastrar(produto);
        Assert.assertNotNull(produtoCadastrado);
    }

    @Test
    public void validaConsultar() {
        Produto produto = createProduto();
        Produto produtoCadastrado = this.repository.cadastrar(produto);
        Assert.assertNotNull(produtoCadastrado);

        Produto produtoConsultado = this.repository.consultar(produtoCadastrado.getId());
        Assert.assertNotNull(produtoConsultado);
        Assert.assertEquals(produto.getId(), produtoConsultado.getId());
    }

    @Test
    public void validaAlterar() {
        Produto produto = createProduto();
        Produto produtoCadastrado = this.repository.cadastrar(produto);
        Assert.assertNotNull(produtoCadastrado);

        produtoCadastrado.setEstoque(20);
        Produto produtoAlterado = this.repository.alterar(produtoCadastrado);
        Assert.assertNotNull(produtoAlterado);

        Produto produtoConsultado = this.repository.consultar(produtoAlterado.getId());
        Assert.assertNotNull(produtoConsultado);

        Assert.assertEquals(20, (int) produtoConsultado.getEstoque());

    }

    @Test
    public void validaBuscarTodos() {
        Produto produto = createProduto();
        Produto produtoCadastrado = this.repository.cadastrar(produto);
        Assert.assertNotNull(produtoCadastrado);

        Produto produto2 = createProduto();
        Produto produto2Cadastrado = this.repository.cadastrar(produto2);
        Assert.assertNotNull(produto2Cadastrado);

        Collection<Produto> produtos = this.repository.buscarTodos();
        Assert.assertNotNull(produtos);
        Assert.assertEquals(2, produtos.size());
    }

    @Test
    public void validaExcluir() {
        Produto produto = createProduto();
        Produto produtoCadastrado = this.repository.cadastrar(produto);
        Assert.assertNotNull(produtoCadastrado);

        Collection<Produto> produtos = this.repository.buscarTodos();
        Assert.assertNotNull(produtos);

        produtos.forEach(this.repository::excluir);
    }
}
