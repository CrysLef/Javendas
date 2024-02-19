package main.java.br.com.cryslefundes.service.generic;

import main.java.br.com.cryslefundes.repository.Persistente;
import main.java.br.com.cryslefundes.repository.generic.IGenericRepository;

import java.io.Serializable;
import java.util.Collection;

public abstract class GenericService<T extends Persistente, E extends Serializable> implements IGenericService<T, E> {
    protected IGenericRepository<T, E> repository;

    public GenericService(IGenericRepository<T, E> repository) {
        this.repository = repository;
    }

    @Override
    public Boolean cadastrar(T entidade) {
        return this.repository.cadastrar(entidade);
    }

    @Override
    public void excluir(E valor) {
        this.repository.excluir(valor);
    }

    @Override
    public void alterar(T entidade) {
        this.repository.alterar(entidade);
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
