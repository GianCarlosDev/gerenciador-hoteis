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
    public class Reservation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "reservation_id")
        private Long reservationId;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy  HH:mm")
        private LocalDateTime checkin;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy  HH:mm")
        private LocalDateTime checkout;

        @ManyToOne
        @JoinColumn(name = "room_id",nullable = false)
        private Room room;

        @ManyToOne
        @JoinColumn(name = "user_id",nullable = false)
        private User user;
    }
