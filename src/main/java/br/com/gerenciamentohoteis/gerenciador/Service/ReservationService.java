package br.com.gerenciamentohoteis.gerenciador.Service;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.CreateReservationDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListReservationsByClientDTO;
import br.com.gerenciamentohoteis.gerenciador.Entity.Hotel;
import br.com.gerenciamentohoteis.gerenciador.Entity.Room;
import br.com.gerenciamentohoteis.gerenciador.Entity.Reservation;
import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.QuartoNotFoundException;
import br.com.gerenciamentohoteis.gerenciador.Repository.HotelRepository;
import br.com.gerenciamentohoteis.gerenciador.Repository.RoomRepository;
import br.com.gerenciamentohoteis.gerenciador.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ReservationService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public CreateReservationDTO booking(String nomeHotel, Integer numero, CreateReservationDTO createReservationDto){
        Room room = roomRepository.findByRoomPutNumberAndHotel(numero,nomeHotel)
                .orElseThrow(()-> new QuartoNotFoundException("quarto não encontrado"));
        if(!room.getAvailable()){
            throw new QuartoNotFoundException("quarto reservado para essa data");
        }
        Reservation reservation = new Reservation();
        reservation.setCheckin(createReservationDto.getCheckin());
        reservation.setCheckout(createReservationDto.getCheckout());
        reservation.setRoom(room);
        reservationRepository.save(reservation);
        room.setAvailable(false);
        roomRepository.save(room);
        return createReservationDto;
    }
    public Stream<ListReservationsByClientDTO> checkingReservation(Long userId){
        List<Reservation> reservations = reservationRepository.findByUserUserId(userId);
        return reservations.stream().map(
                entity -> {
                    Room room = entity.getRoom();
                    Hotel hotel = room.getHotel();
                          return new ListReservationsByClientDTO(
                                  room.getNumber(),
                                  room.getNightPrice(),
                                  hotel.getNameHotel(),
                                  entity.getCheckin(),
                                  entity.getCheckout()
                            );
                });
    }
}

