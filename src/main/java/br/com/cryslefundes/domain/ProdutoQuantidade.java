package main.java.br.com.cryslefundes.domain;

import main.java.br.com.cryslefundes.repository.Persistente;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO_QUANTIDADE")
public class ProdutoQuantidade implements Persistente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;
    @Column(nullable = false)
    private Integer quantidade;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "venda_id_fk",
            foreignKey = @ForeignKey(name = "fk_produto_quantidade_venda"),
            referencedColumnName = "id",
            nullable = false
    )
    private Venda venda;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Produto produto;

    public ProdutoQuantidade() {
        this.valorTotal = BigDecimal.ZERO;
        this.quantidade = 0;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void adicionarAoCarrinho(Integer quantidade) {
        this.quantidade += quantidade;
        BigDecimal novoValor = produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        this.valorTotal = this.valorTotal.add(novoValor);
        produto.setEstoque(produto.getEstoque() - quantidade);
    }

    public void removerDoCarrinho(Integer quantidade) {
        this.quantidade -= quantidade;
        BigDecimal novoValor = produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        this.valorTotal = this.valorTotal.subtract(novoValor);
        produto.setEstoque(produto.getEstoque() - quantidade);
    }
}
