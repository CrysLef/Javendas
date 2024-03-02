package main.java.br.com.cryslefundes.service.generic;

import main.java.br.com.cryslefundes.domain.useCases.GenericUseCase;
import main.java.br.com.cryslefundes.repository.Persistente;
import main.java.br.com.cryslefundes.repository.generic.IGenericRepository;

import java.io.Serializable;
import java.util.Collection;

public abstract class GenericService<T extends Persistente, E extends Serializable> implements GenericUseCase<T, E> {
    protected IGenericRepository<T, E> repository;

    public GenericService(IGenericRepository<T, E> repository) {
        this.repository = repository;
    }

    @Override
    public T cadastrar(T entidade) {
        return this.repository.cadastrar(entidade);
    }

    @Override
    public void excluir(T entidade) {
        this.repository.excluir(entidade);
    }

    @Override
    public T alterar(T entidade) {
        return this.repository.alterar(entidade);
    }

    @Override
    public T consultar(E valor) {
        return this.repository.consultar(valor);
    }

    @Override
    public Collection<T> buscarTodos() {
        return this.repository.buscarTodos();
    }
}
