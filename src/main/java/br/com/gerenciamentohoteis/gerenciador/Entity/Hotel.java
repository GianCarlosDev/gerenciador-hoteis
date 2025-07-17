package br.com.gerenciamentohoteis.gerenciador.Entity;

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
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Hotel")
    private String nomeHotel;
    @Column(unique = true, length = 260, name = "Endereço")
    private String endereco;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Quarto> quartos = new ArrayList<>();
}
