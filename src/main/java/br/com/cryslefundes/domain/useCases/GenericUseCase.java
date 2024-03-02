package main.java.br.com.cryslefundes.domain.useCases;

import main.java.br.com.cryslefundes.repository.Persistente;

import java.io.Serializable;
import java.util.Collection;

public interface GenericUseCase<T extends Persistente, E extends Serializable> {
    T cadastrar(T entidade);
    void excluir(T entidade);
    T alterar(T entidade);
    T consultar(E valor);
    Collection<T> buscarTodos();
}
