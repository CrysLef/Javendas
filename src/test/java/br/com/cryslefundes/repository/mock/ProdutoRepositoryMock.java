package test.java.br.com.cryslefundes.repository.mock;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.repository.IProdutoRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ProdutoRepositoryMock implements IProdutoRepository {
    Produto produto1 = new Produto();
    Produto produto2 = new Produto();

    @Override
    public Boolean cadastrar(Produto entidade) {
        return true;
    }

    @Override
    public void excluir(Long valor) {

    }

    @Override
    public void alterar(Produto entidade) {

    }

    @Override
    public Produto consultar(Long valor) {
        if (produto1.getCodigo().equals(valor)) {
            return produto1;
        } else if (produto2.getCodigo().equals(valor)) {
            return produto2;
        }
        return null;
    }

    @Override
    public Collection<Produto> buscarTodos() {
        Collection<Produto> produtos = new ArrayList<>();

        if (produto1 != null && produto2 != null) {
            produto1.setCodigo(34123L);
            produto2.setCodigo(43151L);
            produtos.add(produto1);
            produtos.add(produto2);
            return produtos;
        }
        return null;
    }
}
