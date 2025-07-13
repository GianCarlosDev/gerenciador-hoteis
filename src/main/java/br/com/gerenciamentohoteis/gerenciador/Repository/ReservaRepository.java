package br.com.gerenciamentohoteis.gerenciador.Repository;

import br.com.gerenciamentohoteis.gerenciador.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
}
