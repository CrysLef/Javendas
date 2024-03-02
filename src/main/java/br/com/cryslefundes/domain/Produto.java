package main.java.br.com.cryslefundes.domain;

import main.java.br.com.cryslefundes.repository.Persistente;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTOS")
public class Produto implements Persistente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20, unique = true)
    private Long codigo;
    @Column(nullable = false)
    private String nome;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;
    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
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
