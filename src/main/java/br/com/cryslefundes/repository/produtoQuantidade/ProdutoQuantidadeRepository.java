package main.java.br.com.cryslefundes.repository.produtoQuantidade;

import main.java.br.com.cryslefundes.domain.ProdutoQuantidade;
import main.java.br.com.cryslefundes.repository.generic.GenericRepository;

public class ProdutoQuantidadeRepository extends GenericRepository<ProdutoQuantidade, Long> implements IProdutoQuantidadeRepository {
    public ProdutoQuantidadeRepository() { super(ProdutoQuantidade.class); }
}
