package main.java.br.com.cryslefundes.repository.generic;

import main.java.br.com.cryslefundes.repository.Persistente;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericRepository<T extends Persistente, E extends Serializable> {
    Boolean cadastrar(T entidade);
    void excluir(E valor);
    void alterar(T entidade);
    T consultar(E valor);

    Collection<T> buscarTodos();

}
