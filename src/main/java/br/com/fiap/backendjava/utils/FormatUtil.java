package br.com.fiap.backendjava.utils;


import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor
public class FormatUtil {
    public static final String DATE_TIME_FORMATTER = "dd/MM/yyyy HH:mm";
    public static final String DATE_FORMATTER = "dd/MM/yyyy";

    public static final String TIME_FORMATTER = "HH:mm";

    public static String getLocalDateTimeFormatted(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER)) : null;
    }

    public static String getLocalDateFormatted(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern(DATE_FORMATTER)) : null;
    }

    public static String getTimeFormatted(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern(TIME_FORMATTER)) : null;
    }

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
