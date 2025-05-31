package br.com.fiap.backendjava.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_distribuicao")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Distribuicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_distribuicao", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_doacao")
    private Doacao doacao;

    private Integer qtdDestinada;

    private LocalDate dataDistribuicao;

    @ManyToOne
    @JoinColumn(name = "id_pessoa_atendida")
    private User user;
}
