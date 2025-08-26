package br.com.gerenciamentohoteis.gerenciador.Repository;

import br.com.gerenciamentohoteis.gerenciador.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findByHotelHotelId(Long hotelId);

    @Query("SELECT q FROM Room q WHERE q.number = :number AND q.hotel.nameHotel = :nameHotel")
    Optional<Room> findByRoomPutNumberAndHotel(@Param("number") Integer number, @Param("nameHotel") String nameHotel);
}
