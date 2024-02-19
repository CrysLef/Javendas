package main.java.br.com.cryslefundes.repository.generic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import main.java.annotation.ColunaTabela;
import main.java.annotation.Tabela;
import main.java.br.com.cryslefundes.exceptions.TableException;
import main.java.br.com.cryslefundes.repository.Persistente;
import main.java.br.com.cryslefundes.repository.jdbc.ConnectionFactory;

public abstract class GenericRepository<T extends Persistente, E extends Serializable> implements IGenericRepository<T, E> {
    private PreparedStatement statement;
    private ResultSet rset;
    public abstract Class<T> getTipoClasse();
    protected abstract String getInsertQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    protected abstract String getSelectQuery();
    protected abstract void setInsertQueryParameters(PreparedStatement statement, T entidade) throws SQLException;
    protected abstract void setUpdateQueryParameters(PreparedStatement statement, T entidade) throws SQLException;
    protected abstract void setDeleteQueryParameters(PreparedStatement statement, E valor) throws SQLException;
    protected abstract void setSelectQueryParameters(PreparedStatement statement, E valor) throws SQLException;

    public GenericRepository(){}

    private void setValueByType(Class<?> classField, ResultSet rs, String fieldName, Method method, T entidade) throws SQLException, InvocationTargetException, IllegalAccessException {
        if (classField.equals(Integer.class)) {
            Integer valor = rs.getInt(fieldName);
            method.invoke(entidade, valor);
        } else if (classField.equals(Long.class)) {
            Long valor = rs.getLong(fieldName);
            method.invoke(entidade, valor);
        } else if (classField.equals(BigDecimal.class)) {
            BigDecimal valor = rs.getBigDecimal(fieldName);
            method.invoke(entidade, valor);
        } else if (classField.equals(String.class)) {
            String valor = rs.getString(fieldName);
            method.invoke(entidade, valor);
        }
    }

    private String getTableName() throws TableException {
        if (getTipoClasse().isAnnotationPresent(Tabela.class)) {
            Tabela tableName = getTipoClasse().getAnnotation(Tabela.class);
            return tableName.value();
        } else {
            throw new TableException("Tabela " + getTipoClasse().getName() + " não encontrada!");
        }
    }

    @Override
    public Boolean cadastrar(T entidade) {
        Connection database = getConnection();
        try {
            statement = database.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            setInsertQueryParameters(statement, entidade);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                rset = statement.getGeneratedKeys();
                if (rset.next()) {
                    entidade.setId(rset.getLong(1));
                }
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(database, statement, rset);
        }
        return false;
    }

    @Override
    public void excluir(E valor) {
        Connection database = getConnection();
        try {
            statement = database.prepareStatement(getDeleteQuery());
            setDeleteQueryParameters(statement, valor);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(database, statement, null);
        }
    }

    @Override
    public void alterar(T entidade) {
        Connection database = getConnection();
        try {
            statement = database.prepareStatement(getUpdateQuery());
            setUpdateQueryParameters(statement, entidade);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(database, statement, null);
        }
    }

    @Override
    public T consultar(E valor) {
        Connection database = null;
        try {
            database = getConnection();
            statement = database.prepareStatement(getSelectQuery());
            setSelectQueryParameters(statement, valor);
            rset = statement.executeQuery();

            if (rset.next()) {
                T entidade = getTipoClasse().getConstructor().newInstance();
                Field[] fields = entidade.getClass().getDeclaredFields();
                for (Field field: fields) {
                    if (field.isAnnotationPresent(ColunaTabela.class)) {
                        ColunaTabela coluna = field.getAnnotation(ColunaTabela.class);
                        String dbName = coluna.dbName();
                        String setJavaName = coluna.setJavaName();
                        Class<?> classField = field.getType();
                        Method method = entidade.getClass().getMethod(setJavaName, classField);
                        setValueByType(classField, rset, dbName, method, entidade);
                    }
                }
                return entidade;
            }
        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(database, statement, rset);
        }
        return null;
    }

    @Override
    public Collection<T> buscarTodos() {
        List<T> entidades = new ArrayList<>();
        Connection database = null;
        try {
            database = getConnection();
            statement = database.prepareStatement("SELECT * FROM " + getTableName());
            rset = statement.executeQuery();

            while (rset.next()) {
                T entidade = getTipoClasse().getConstructor().newInstance();
                Field[] fields = entidade.getClass().getDeclaredFields();
                for (Field field: fields) {
                    if (field.isAnnotationPresent(ColunaTabela.class)) {
                        ColunaTabela coluna = field.getAnnotation(ColunaTabela.class);
                        String dbName = coluna.dbName();
                        String setJavaName = coluna.setJavaName();
                        Class<?> classField = field.getType();
                        Method method = entidade.getClass().getMethod(setJavaName, classField);
                        setValueByType(classField, rset, dbName, method, entidade);
                    }
                }
                entidades.add(entidade);
            }
        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException | TableException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(database, statement, rset);
        }
        return entidades;
    }

    private static Connection getConnection() {
        try {
            return ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void closeConnection(Connection connection, PreparedStatement statement, ResultSet rset) {
        try {
            ConnectionFactory.closeConnection(connection, statement, rset);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
