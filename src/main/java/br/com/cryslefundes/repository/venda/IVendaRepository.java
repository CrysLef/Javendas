package main.java.br.com.cryslefundes.repository.venda;

import main.java.br.com.cryslefundes.domain.Venda;
import main.java.br.com.cryslefundes.repository.generic.IGenericRepository;

public interface IVendaRepository extends IGenericRepository<Venda, Long> {
    Venda finalizarVenda(Venda venda);
    Venda cancelarVenda(Venda venda);
    Venda consultaLazy(Long id);
}
