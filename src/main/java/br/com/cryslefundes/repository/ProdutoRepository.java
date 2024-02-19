package main.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.repository.generic.GenericRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoRepository extends GenericRepository<Produto, Long> implements IProdutoRepository {

    public ProdutoRepository() {
        super();
    }

    @Override
    public Class<Produto> getTipoClasse() {
        return Produto.class;
    }

    @Override
    protected String getInsertQuery() {
        return """
                INSERT INTO PRODUTOS
                (ID, CODIGO, NOME, DESCRICAO, VALOR, ESTOQUE)
                VALUES (nextval('sq_produtos'),?,?,?,?,?);
                """;
    }

    @Override
    protected String getUpdateQuery() {
        return """
                UPDATE PRODUTOS SET
                NOME = ?, DESCRICAO = ?, VALOR = ?, ESTOQUE = ?
                WHERE CODIGO = ?;
                """;
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM PRODUTOS WHERE CODIGO = ?";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM PRODUTOS WHERE CODIGO = ?";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Produto entidade) throws SQLException {
        statement.setLong(1, entidade.getCodigo());
        statement.setString(2, entidade.getNome());
        statement.setString(3, entidade.getDescricao());
        statement.setBigDecimal(4, entidade.getValor());
        statement.setInt(5, entidade.getEstoque());
    }

    @Override
    protected void setUpdateQueryParameters(PreparedStatement statement, Produto entidade) throws SQLException {
        statement.setString(1, entidade.getNome());
        statement.setString(2, entidade.getDescricao());
        statement.setBigDecimal(3, entidade.getValor());
        statement.setInt(4, entidade.getEstoque());
        statement.setLong(5, entidade.getCodigo());
    }

    @Override
    protected void setDeleteQueryParameters(PreparedStatement statement, Long valor) throws SQLException {
        statement.setLong(1, valor);
    }

    @Override
    protected void setSelectQueryParameters(PreparedStatement statement, Long valor) throws SQLException {
        statement.setLong(1, valor);
    }
}
