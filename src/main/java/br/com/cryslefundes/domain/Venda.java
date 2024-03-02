package main.java.br.com.cryslefundes.domain;

import main.java.br.com.cryslefundes.domain.enums.StatusVenda;
import main.java.br.com.cryslefundes.repository.Persistente;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "VENDAS")
public class Venda implements Persistente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private Long codigo;
    @Column(name = "status_da_venda", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusVenda statusVenda;
    @Column(name = "data_da_venda", nullable = false)
    private Instant dataVenda;
    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "cliente_id_fk",
            foreignKey = @ForeignKey(name = "fk_venda_cliente"),
            referencedColumnName = "id",
            nullable = false
    )
    private Cliente cliente;
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private Set<ProdutoQuantidade> produtos;

    public Venda() {
        this.produtos = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusVenda getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(StatusVenda statusVenda) {
        this.statusVenda = statusVenda;
    }

    public Instant getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Instant dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ProdutoQuantidade> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoQuantidade> produtos) {
        this.produtos = produtos;
    }

    private void validarStatus() {
        if (this.statusVenda.equals(StatusVenda.CONCLUIDA)) throw new UnsupportedOperationException("IMPOSSÍVEL ALTERAR VENDA FINALIZADA");
    }

    public void recalcularValorTotalVenda() {
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ProdutoQuantidade produto: produtos) {
            valorTotal = valorTotal.add(produto.getValorTotal());
        }
        this.valorTotal = valorTotal;

    }

    public void adicionarProduto(Produto produto, Integer quantidade) {
        validarStatus();
        Optional<ProdutoQuantidade> produtoQuantidade = produtos.stream()
                .filter(pq -> pq.getProduto().getCodigo().equals(produto.getCodigo()))
                .findAny();

        if (produtoQuantidade.isPresent()) {
            ProdutoQuantidade pq = produtoQuantidade.get();
            pq.adicionarAoCarrinho(quantidade);
        } else {
            ProdutoQuantidade pq = new ProdutoQuantidade();
            pq.setVenda(this);
            pq.setProduto(produto);
            pq.adicionarAoCarrinho(quantidade);
            produtos.add(pq);
        }
        recalcularValorTotalVenda();
    }

    public void removerProduto(Produto produto, Integer quantidade) {
        validarStatus();
        Optional<ProdutoQuantidade> produtoQuantidade = produtos.stream()
                .filter(pq -> pq.getProduto().getCodigo().equals(produto.getCodigo()))
                .findAny();

        if (produtoQuantidade.isPresent()) {
            ProdutoQuantidade pq = produtoQuantidade.get();
            if (pq.getQuantidade() > quantidade) {
                pq.removerDoCarrinho(quantidade);
                recalcularValorTotalVenda();
            } else {
                produtos.remove(pq);
                recalcularValorTotalVenda();
            }
        }
    }

    public void removerTodosProdutos() {
        validarStatus();
        produtos.clear();
        this.valorTotal = BigDecimal.ZERO;
    }

    public Integer obtemQuantidadeTotalProdutos() {
        int quantidadeTotal = produtos.stream()
                .reduce(
                        0,
                        (partialCountResult, pq) -> partialCountResult + pq.getQuantidade(),
                        Integer::sum
                );
        return quantidadeTotal;
    }


}
