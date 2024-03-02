package main.java.br.com.cryslefundes.repository.venda;

import main.java.br.com.cryslefundes.domain.Cliente;
import main.java.br.com.cryslefundes.domain.Produto;
import main.java.br.com.cryslefundes.domain.Venda;
import main.java.br.com.cryslefundes.repository.generic.GenericRepository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class VendaRepository extends GenericRepository<Venda, Long> implements IVendaRepository{
    public VendaRepository() {
        super(Venda.class);
    }

    @Override
    public Venda cadastrar(Venda entidade) {
        openConnection();

        entidade.getProdutos().forEach(p -> {
            Produto produto = entityManager.merge(p.getProduto());
            p.setProduto(produto);
        });

        Cliente cliente = entityManager.merge(entidade.getCliente());
        entidade.setCliente(cliente);

        entityManager.persist(entidade);

        closeConnection();
        return entidade;
    }

    @Override
    public Venda finalizarVenda(Venda venda) {
        return super.alterar(venda);
    }

    @Override
    public Venda cancelarVenda(Venda venda) {
        return super.alterar(venda);
    }

    @Override
    public Venda consultaLazy(Long id) {
        openConnection();

        var criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Venda> query = criteriaBuilder.createQuery(Venda.class);
        Root<Venda> root = query.from(Venda.class);
        root.fetch("cliente");
        root.fetch("produtos");
        query.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        TypedQuery<Venda> typedQuery = entityManager.createQuery(query);
        Venda venda = typedQuery.getSingleResult();

        closeConnection();
        return venda;
    }
}
