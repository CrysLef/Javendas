package test.java.br.com.cryslefundes.repository.mock;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.cryslefundes.repository.IProdutoRepository;

import java.util.Collection;

public class ProdutoRepositoryMock implements IProdutoRepository {
    @Override
    public Boolean cadastrar(Produto entidade) throws TipoChaveNaoEncontradaException {
        return true;
    }

    @Override
    public void excluir(String valor) {

    }

    @Override
    public void alterar(Produto entidade) throws TipoChaveNaoEncontradaException {

    }

    @Override
    public Produto consultar(String valor) {
        Produto produto1 = new Produto();
        produto1.setCodigo(valor);
        return produto1;
    }

    @Override
    public Collection<Produto> buscarTodos() {
        return null;
    }
}
