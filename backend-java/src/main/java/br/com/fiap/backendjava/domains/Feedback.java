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

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_Feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feedback", nullable = false)
    private Integer id;

    private Integer nota;

    @Column(length = 500)
    private String comentario;

    @Column(name = "data_feedback", nullable = false)
    private LocalDateTime dataFeedback;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_avaliado", nullable = false)
    private User idAvaliado;


}
