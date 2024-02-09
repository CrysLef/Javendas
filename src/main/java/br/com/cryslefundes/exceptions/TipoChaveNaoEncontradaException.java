package main.java.br.com.cryslefundes.exceptions;

import java.io.Serial;

public class TipoChaveNaoEncontradaException extends Exception {
    @Serial
    private static final long serialVersionUID = -1389494676398525746L;

    public TipoChaveNaoEncontradaException(String msg, Throwable e) {
        super(msg, e);
    }

    public TipoChaveNaoEncontradaException(String msg) {
        this(msg, null);
    }

}
