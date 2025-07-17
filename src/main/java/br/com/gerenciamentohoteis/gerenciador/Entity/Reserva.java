package br.com.gerenciamentohoteis.gerenciador.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class Reserva {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nomeCliente;
        @Column(length = 14)
        private String cpf;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy  HH:mm")
        private LocalDateTime checkin;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy  HH:mm")
        private LocalDateTime checkout;

        @ManyToOne
        @JoinColumn(name = "quarto_id",nullable = false)
        private Quarto quarto;
    }
