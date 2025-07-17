package br.com.gerenciamentohoteis.gerenciador.Repository;

import br.com.gerenciamentohoteis.gerenciador.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {

    List<Reserva> findByCpf(String cpf);
}