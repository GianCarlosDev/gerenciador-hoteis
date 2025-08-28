package br.com.gerenciamentohoteis.gerenciador.Repository;

import br.com.gerenciamentohoteis.gerenciador.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByUserUserId(Long userId);

}