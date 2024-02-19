package main.java.br.com.cryslefundes.repository;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.repository.generic.GenericRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteRepository extends GenericRepository<Cliente, Long> implements IClienteRepository{

    public ClienteRepository() {
        super();
    }

    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    protected String getInsertQuery() {
        return """
                INSERT INTO CLIENTES
                (ID, CPF, NOME, TELEFONE, EMAIL, ENDERECO, CIDADE, ESTADO)
                VALUES (nextval('sq_clientes'),?,?,?,?,?,?,?);
                """;
    }

    @Override
    protected String getUpdateQuery() {
        return """
                UPDATE CLIENTES SET
                NOME = ?, TELEFONE = ?, EMAIL = ?, ENDERECO = ?, CIDADE = ?, ESTADO = ?
                WHERE CPF = ?;
                """;
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM CLIENTES WHERE CPF = ?;";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM CLIENTES WHERE CPF = ?;";
    }

    @Override
    protected void setInsertQueryParameters(PreparedStatement statement, Cliente entidade) throws SQLException {
        statement.setLong(1, entidade.getCpf());
        statement.setString(2, entidade.getNome());
        statement.setString(3, entidade.getTelefone());
        statement.setString(4, entidade.getEmail());
        statement.setString(5, entidade.getEndereco());
        statement.setString(6, entidade.getCidade());
        statement.setString(7, entidade.getEstado());
    }

    @Override
    protected void setUpdateQueryParameters(PreparedStatement statement, Cliente entidade) throws SQLException{
        statement.setString(1, entidade.getNome());
        statement.setString(2, entidade.getTelefone());
        statement.setString(3, entidade.getEmail());
        statement.setString(4, entidade.getEndereco());
        statement.setString(5, entidade.getCidade());
        statement.setString(6, entidade.getEstado());
        statement.setLong(7, entidade.getCpf());
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
