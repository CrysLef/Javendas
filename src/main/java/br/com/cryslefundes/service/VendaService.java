package main.java.br.com.cryslefundes.service;

import main.java.br.com.cryslefundes.domain.Venda;
import main.java.br.com.cryslefundes.domain.useCases.VendaUseCase;
import main.java.br.com.cryslefundes.repository.venda.IVendaRepository;
import main.java.br.com.cryslefundes.service.generic.GenericService;

public class VendaService extends GenericService<Venda, Long> implements VendaUseCase {
    public VendaService(IVendaRepository vendaRepository) {
        super(vendaRepository);
    }
}
