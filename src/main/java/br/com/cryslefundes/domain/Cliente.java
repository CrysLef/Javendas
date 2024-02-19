package main.java.br.com.cryslefundes.domain;

import main.java.annotation.ColunaTabela;
import main.java.annotation.Tabela;
import main.java.br.com.cryslefundes.repository.Persistente;

@Tabela("CLIENTES")
public class Cliente implements Persistente {
    @ColunaTabela(dbName = "id", setJavaName = "setId")
    private Long id;
    @ColunaTabela(dbName = "cpf", setJavaName = "setCpf")
    private Long cpf;
    @ColunaTabela(dbName = "nome", setJavaName = "setNome")
    private String nome;
    @ColunaTabela(dbName = "telefone", setJavaName = "setTelefone")
    private String telefone;
    @ColunaTabela(dbName = "email", setJavaName = "setEmail")
    private String email;
    @ColunaTabela(dbName = "endereco", setJavaName = "setEndereco")
    private String endereco;
    @ColunaTabela(dbName = "cidade", setJavaName = "setCidade")
    private String cidade;
    @ColunaTabela(dbName = "estado", setJavaName = "setEstado")
    private String estado;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
