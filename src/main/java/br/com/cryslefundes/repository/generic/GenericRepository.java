package main.java.br.com.cryslefundes.repository.generic;

import java.io.Serializable;

import java.util.*;

import main.java.br.com.cryslefundes.repository.Persistente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class GenericRepository<T extends Persistente, E extends Serializable> implements IGenericRepository<T, E> {
    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;
    private Class<T> persistenteClass;

    public GenericRepository(Class<T> persistenteClass) {
        this.persistenteClass = persistenteClass;
    }

    protected void openConnection() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Javendas");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
    }

    protected void closeConnection() {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public T cadastrar(T entidade) {
        openConnection();
        entityManager.persist(entidade);
        closeConnection();
        return entidade;
    }

    @Override
    public void excluir(T entidade) {
        openConnection();
        entidade = entityManager.merge(entidade);
        entityManager.remove(entidade);
        closeConnection();
    }

    @Override
    public T alterar(T entidade) {
        openConnection();
        entidade = entityManager.merge(entidade);
        closeConnection();
        return entidade;
    }

    @Override
    public T consultar(E valor) {
        openConnection();
        T entidade = entityManager.find(this.persistenteClass, valor);
        closeConnection();
        return entidade;
    }

    @Override
    public Collection<T> buscarTodos() {
        String nomeClasse = persistenteClass.getSimpleName();
        openConnection();
        List<T> entidades = entityManager
                .createQuery("SELECT e FROM " + nomeClasse + " e", this.persistenteClass)
                .getResultList();
        closeConnection();
        return entidades;
    }
}
