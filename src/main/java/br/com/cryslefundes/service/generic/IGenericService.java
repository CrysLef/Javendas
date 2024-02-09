package main.java.br.com.cryslefundes.service.generic;

import main.java.br.com.cryslefundes.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.cryslefundes.repository.Persistente;

import java.util.Collection;

public interface IGenericService<T extends Persistente, E> {
    Boolean cadastrar(T entidade) throws TipoChaveNaoEncontradaException;
    void excluir(E valor);
    void alterar(T entidade) throws TipoChaveNaoEncontradaException;
    T consultar(E valor);

    Collection<T> buscarTodos();
}
