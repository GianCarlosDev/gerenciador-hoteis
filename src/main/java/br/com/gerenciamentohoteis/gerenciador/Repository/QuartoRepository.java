package br.com.gerenciamentohoteis.gerenciador.Repository;

import br.com.gerenciamentohoteis.gerenciador.Entity.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface QuartoRepository extends JpaRepository<Quarto,Long> {
    List<Quarto> findByHotelId(Long hotelId);

    @Query("SELECT q FROM Quarto q WHERE q.numero = :numero AND q.hotel.nomeHotel = :nomeHotel")
    Optional<Quarto> findByNumeroAndNomeHotel(@Param("numero") Integer numero, @Param("nomeHotel") String nomeHotel);
}
