package main.java.br.com.cryslefundes.service.generic;

import main.java.br.com.cryslefundes.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.cryslefundes.repository.Persistente;
import main.java.br.com.cryslefundes.repository.generic.IGenericRepository;

import java.util.Collection;

public abstract class GenericService<T extends Persistente, E> implements IGenericService<T, E> {
    protected IGenericRepository<T, E> repository;

    public GenericService(IGenericRepository<T, E> repository) {
        this.repository = repository;
    }

    @Override
    public Boolean cadastrar(T entidade) throws TipoChaveNaoEncontradaException {
        return this.repository.cadastrar(entidade);
    }

    @Override
    public void excluir(E valor) {
        this.repository.excluir(valor);
    }

    @Override
    public void alterar(T entidade) throws TipoChaveNaoEncontradaException {
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
