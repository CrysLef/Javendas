package main.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.repository.generic.GenericRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoRepository extends GenericRepository<Produto, Long> implements IProdutoRepository {

    public ProdutoRepository() {
        super(Produto.class);
    }
}
