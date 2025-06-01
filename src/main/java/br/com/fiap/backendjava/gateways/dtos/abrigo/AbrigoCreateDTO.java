package br.com.fiap.backendjava.gateways.dtos.abrigo;

public record AbrigoCreateDTO(
        Integer capacidadeTotal,
        Integer ocupacaoAtual,
        String descricao
) {}
