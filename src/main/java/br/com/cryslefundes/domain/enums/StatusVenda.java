package main.java.br.com.cryslefundes.domain.enums;

public enum StatusVenda {
    INICIADA,
    CONCLUIDA,
    CANCELADA;

    public static StatusVenda getByName(String status) {
        for (StatusVenda s: StatusVenda.values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + status);
    }
}
