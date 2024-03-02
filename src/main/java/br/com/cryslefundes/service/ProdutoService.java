package main.java.br.com.cryslefundes.service;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.domain.useCases.ProdutoUseCase;
import main.java.br.com.cryslefundes.repository.produto.IProdutoRepository;
import main.java.br.com.cryslefundes.service.generic.GenericService;

public class ProdutoService extends GenericService<Produto, Long> implements ProdutoUseCase {
    public ProdutoService(IProdutoRepository produtoRepository) {
        super(produtoRepository);
    }
}
