package br.com.fiap.backendjava.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor
public class FormatUtil {

    // Remove todos os caracteres não numéricos
    public static String limparTelefone(String telefone) {
        return telefone != null ? telefone.replaceAll("\\D", "") : null;
    }

    // Coloca primeira letra maiúscula e o resto minúsculo em cada palavra
    public static String formatarNomeCompleto(String nome) {
        return Arrays.stream(nome.trim().split("\\s+"))
                .map(palavra -> palavra.substring(0, 1).toUpperCase() + palavra.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
