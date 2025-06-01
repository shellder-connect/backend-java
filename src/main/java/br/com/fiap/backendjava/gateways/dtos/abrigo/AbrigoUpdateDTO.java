package br.com.fiap.backendjava.gateways.dtos.abrigo;

public record AbrigoUpdateDTO(
        Integer capacidadeTotal,
        Integer ocupacaoAtual,
        String descricao
) {}
