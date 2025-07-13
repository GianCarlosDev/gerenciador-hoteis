package br.com.gerenciamentohoteis.gerenciador.Entity;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.TiposQuartos;
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
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TiposQuartos quartos;
    private Double precoNoite;
    private Boolean disponivel = true;
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "quarto",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Reserva> reservas = new ArrayList<>();
}
