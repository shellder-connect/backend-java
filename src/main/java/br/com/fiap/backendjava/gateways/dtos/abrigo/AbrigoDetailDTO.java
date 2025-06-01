package br.com.fiap.backendjava.gateways.dtos.abrigo;

public record AbrigoDetailDTO(
        Integer id,
        Integer capacidadeTotal,
        Integer ocupacaoAtual,
        String descricao
) {}
