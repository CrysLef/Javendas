package main.java.br.com.cryslefundes.domain;

import main.java.annotation.TipoChave;
import main.java.br.com.cryslefundes.repository.Persistente;

import java.math.BigDecimal;

public class Produto implements Persistente {
    @TipoChave("getCodigo")
    private Long codigo;
    private String nome;
    private String descricao;
    private BigDecimal valor;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
