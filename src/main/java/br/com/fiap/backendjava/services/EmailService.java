package br.com.fiap.backendjava.services;

public interface EmailService {
    void enviarEmail(String destinatario, String assunto, String msg);
}
