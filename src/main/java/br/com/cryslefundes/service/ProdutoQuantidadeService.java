package main.java.br.com.cryslefundes.service;

import main.java.br.com.cryslefundes.domain.ProdutoQuantidade;
import main.java.br.com.cryslefundes.domain.useCases.ProdutoQuantidadeUseCase;
import main.java.br.com.cryslefundes.repository.produtoQuantidade.IProdutoQuantidadeRepository;
import main.java.br.com.cryslefundes.service.generic.GenericService;

import java.math.BigDecimal;

public class ProdutoQuantidadeService extends GenericService<ProdutoQuantidade, Long> implements ProdutoQuantidadeUseCase {
    public ProdutoQuantidadeService(IProdutoQuantidadeRepository produtoQuantidadeRepository) {
        super(produtoQuantidadeRepository);
    }
}
