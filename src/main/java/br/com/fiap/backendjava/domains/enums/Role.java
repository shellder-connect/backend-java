package br.com.fiap.backendjava.domains.enums;

public enum Role {
    ADMIN(1, "Admin"),
    VOLUNTARIO(2, "Voluntário"),
    EMPRESAPARCEIRA(3, "Empresa Parceira"),
    SOLICITANTE(4, "Solicitante"),
    PROFISSIONALSAUDE(5, "Profissional de Saúde");

    private int cod;
    private String descricao;

    Role(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Role toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Role x : Role.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("ID Inválido: " + cod);
    }
}
