package main.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.repository.generic.GenericRepository;

public class ProdutoRepository extends GenericRepository<Produto, Long> implements IProdutoRepository {

    public ProdutoRepository() {
        super();
    }

    @Override
    public Class<Produto> getTipoClasse() {
        return Produto.class;
    }

    @Override
    public void atualizarDados(Produto entidade, Produto entidadeCadastrado) {
        entidadeCadastrado.setNome(entidade.getNome());
        entidadeCadastrado.setCodigo(entidade.getCodigo());
        entidadeCadastrado.setDescricao(entidade.getDescricao());
        entidadeCadastrado.setValor(entidade.getValor());
    }
}
