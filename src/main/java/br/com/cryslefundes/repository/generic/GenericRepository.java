package main.java.br.com.cryslefundes.repository.generic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.java.annotation.TipoChave;
import main.java.br.com.cryslefundes.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.cryslefundes.repository.Persistente;

public abstract class GenericRepository<T extends Persistente, E extends Serializable> implements IGenericRepository<T, E> {
    private SingletonMap singletonMap;
    public abstract Class<T> getTipoClasse();
    public abstract void atualizarDados(T entidade, T entidadeCadastrado);

    public GenericRepository() {
        this.singletonMap = SingletonMap.getInstance();
    }

    public E getChave(T entidade) throws TipoChaveNaoEncontradaException {
        Field[] fields = entidade.getClass().getDeclaredFields();
        String msg = "Chave principal do objeto" + entidade.getClass() + " não encontrada";
        E valorDeRetorno = null;

        for (Field field: fields) {
            if (field.isAnnotationPresent(TipoChave.class)) {
                TipoChave tipoChave = field.getAnnotation(TipoChave.class);
                String nomeMetodo = tipoChave.value();

                try {
                    Method method = entidade.getClass().getMethod(nomeMetodo);
                    valorDeRetorno = (E) method.invoke(entidade);
                    return valorDeRetorno;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new TipoChaveNaoEncontradaException(msg, e);
                }
            }
        }

        if (valorDeRetorno == null) {
            System.out.println("***** ERRO *****" + msg);
            throw new TipoChaveNaoEncontradaException(msg);
        }
        return null;
    }

    public Map<E, T> getMapa() {
        Map<E, T> mapaInterno = (Map<E, T>) this.singletonMap.getMap().get(getTipoClasse());
        if (mapaInterno == null) {
            mapaInterno = new HashMap<>();
            this.singletonMap.getMap().put(getTipoClasse(), mapaInterno);
        }
        return mapaInterno;
    }

    @Override
    public Boolean cadastrar(T entidade) throws TipoChaveNaoEncontradaException {
        Map<E, T> mapaInterno = getMapa();
        E chave = getChave(entidade);
        if (mapaInterno.containsKey(chave)) {
            return false;
        }
        mapaInterno.put(chave, entidade);
        return true;
    }

    @Override
    public void excluir(E valor) {
        Map<E, T> mapaInterno = getMapa();
        T objCadastrado = mapaInterno.get(valor);
        if (objCadastrado != null) {
            mapaInterno.remove(valor, objCadastrado);
        }
    }

    @Override
    public void alterar(T entidade) throws TipoChaveNaoEncontradaException {
        Map<E, T> mapaInterno = getMapa();
        E chave = getChave(entidade);
        T objCadastrado = mapaInterno.get(chave);
        if (objCadastrado != null) {
            atualizarDados(entidade, objCadastrado);
        }
    }

    @Override
    public T consultar(E valor) {
        Map<E, T> mapaInterno = getMapa();
        return mapaInterno.get(valor);
    }

    @Override
    public Collection<T> buscarTodos() {
        Map<E, T> mapaInterno = getMapa();
        return mapaInterno.values();
    }
}
