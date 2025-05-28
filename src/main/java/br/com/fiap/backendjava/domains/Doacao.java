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

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_Doacao")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doacao", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_abrigo")
    private Abrigo abrigo;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    private Integer quantidade;
}
