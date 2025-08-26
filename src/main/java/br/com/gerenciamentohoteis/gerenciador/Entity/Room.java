package br.com.gerenciamentohoteis.gerenciador.Entity;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.RoomTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;
    @Enumerated(EnumType.STRING)
    private RoomTypes rooms;
    private Double nightPrice;
    private Boolean available = true;
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
}
