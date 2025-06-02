package br.com.fiap.backendjava.gateways.dtos.abrigo;

import jakarta.validation.constraints.NotBlank;

public record AbrigoCreateDTO(
        Integer capacidadeTotal,
        Integer ocupacaoAtual,
        @NotBlank
        String descricao
) {}
