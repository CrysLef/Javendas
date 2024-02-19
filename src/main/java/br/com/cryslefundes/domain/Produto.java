package main.java.br.com.cryslefundes.domain;

import main.java.annotation.ColunaTabela;
import main.java.annotation.Tabela;
import main.java.br.com.cryslefundes.repository.Persistente;

import java.math.BigDecimal;

@Tabela("PRODUTOS")
public class Produto implements Persistente {
    @ColunaTabela(dbName = "id", setJavaName = "setId")
    private Long id;
    @ColunaTabela(dbName = "codigo", setJavaName = "setCodigo")
    private Long codigo;
    @ColunaTabela(dbName = "nome", setJavaName = "setNome")
    private String nome;
    @ColunaTabela(dbName = "descricao", setJavaName = "setDescricao")
    private String descricao;
    @ColunaTabela(dbName = "valor", setJavaName = "setValor")
    private BigDecimal valor;
    @ColunaTabela(dbName = "estoque", setJavaName = "setEstoque")
    private Integer estoque;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
