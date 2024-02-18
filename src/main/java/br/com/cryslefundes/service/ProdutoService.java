package main.java.br.com.cryslefundes.service;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.repository.IProdutoRepository;
import main.java.br.com.cryslefundes.service.generic.GenericService;

public class ProdutoService extends GenericService<Produto, Long> implements IProdutoService {
    public ProdutoService(IProdutoRepository ProdutoRepository) {
        super(ProdutoRepository);
    }
}
