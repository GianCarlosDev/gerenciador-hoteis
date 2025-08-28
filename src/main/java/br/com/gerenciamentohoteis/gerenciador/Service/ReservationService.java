package br.com.gerenciamentohoteis.gerenciador.Service;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.CreateReservationDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListReservationsByClientDTO;
import br.com.gerenciamentohoteis.gerenciador.Entity.Hotel;
import br.com.gerenciamentohoteis.gerenciador.Entity.Room;
import br.com.gerenciamentohoteis.gerenciador.Entity.Reservation;
import br.com.gerenciamentohoteis.gerenciador.Entity.User;
import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.QuartoNotFoundException;
import br.com.gerenciamentohoteis.gerenciador.Repository.HotelRepository;
import br.com.gerenciamentohoteis.gerenciador.Repository.RoomRepository;
import br.com.gerenciamentohoteis.gerenciador.Repository.ReservationRepository;
import br.com.gerenciamentohoteis.gerenciador.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ReservationService {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public ReservationService(HotelRepository hotelRepository, RoomRepository roomRepository, ReservationRepository reservationRepository, UserRepository userRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public CreateReservationDTO booking(String nameHotel, Integer number, CreateReservationDTO createReservationDto){
        Room room = roomRepository.findByRoomPutNumberAndHotel(number,nameHotel)
                .orElseThrow(()-> new QuartoNotFoundException("Hotel ou quarto não existe"));
        if(!room.getAvailable()){
            throw new QuartoNotFoundException("quarto reservado para essa data");
        }

        Reservation reservation = new Reservation();
        User user = (User) userRepository.findByEmail(createReservationDto.getEmail());
        if (user == null) {
           throw new RuntimeException("Email inválido");
        }

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

