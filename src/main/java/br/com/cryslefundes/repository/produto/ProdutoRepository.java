package main.java.br.com.cryslefundes.repository.produto;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.repository.generic.GenericRepository;

public class ProdutoRepository extends GenericRepository<Produto, Long> implements IProdutoRepository {

    public ProdutoRepository() {
        super(Produto.class);
    }
}
