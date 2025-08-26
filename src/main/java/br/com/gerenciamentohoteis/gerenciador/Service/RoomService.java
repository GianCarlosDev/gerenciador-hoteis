package br.com.gerenciamentohoteis.gerenciador.Service;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.CreatedRoomDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Request.GetRoomByHotelDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Request.UpdateRoomDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListRoomDTO;
import br.com.gerenciamentohoteis.gerenciador.Entity.Hotel;
import br.com.gerenciamentohoteis.gerenciador.Entity.Room;
import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.HotelNotFoundException;
import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.QuartoNotFoundException;
import br.com.gerenciamentohoteis.gerenciador.Repository.HotelRepository;
import br.com.gerenciamentohoteis.gerenciador.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public CreatedRoomDTO CreateRoom(Long hotelId, CreatedRoomDTO createdRoomDto){
        Hotel hotel =  hotelRepository.findById(hotelId)
                .orElseThrow(()-> new HotelNotFoundException("Hotel não foi encontrado"));
        Room room = new Room();
        room.setHotel(hotel);
        room.setRooms(createdRoomDto.getRooms());
        room.setNightPrice(createdRoomDto.getNightPrice());
        room.setNumber(createdRoomDto.getNumber());
        roomRepository.save(room);
        return createdRoomDto;
    }
    public Stream<ListRoomDTO> listRoom(Long hotelId){
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new HotelNotFoundException("Hotel não foi encontrado"));
        List<Room> rooms = roomRepository.findByHotelHotelId(hotelId);
        return rooms.stream().map(entity -> new ListRoomDTO(
                entity.getRooms(),entity.getNumber(),entity.getNightPrice(),entity.getAvailable()
        ));
    }
    public GetRoomByHotelDTO roomByHotel(Long hotelId, Long id){
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new HotelNotFoundException("hotel não foi encontrado"));
        Room room = roomRepository.findById(id).orElseThrow(()-> new QuartoNotFoundException("quarto não encontrado"));

        return new GetRoomByHotelDTO(
                hotel.getNameHotel(), room.getRooms(), room.getNightPrice(),
                room.getAvailable(), room.getNumber());
    }
    public void updateRoom(Long hotelId, Long id, UpdateRoomDTO dto){
        Hotel hotel = hotelRepository.findById(id).
                orElseThrow(()-> new HotelNotFoundException("hotel não foi encontrado"));
        Room room = roomRepository.findById(id).
                orElseThrow(()-> new QuartoNotFoundException("quarto não encontrado"));
        room.setRooms(dto.rooms());
        room.setNightPrice(dto.nightPrice());
        room.setNumber(dto.numero());
        roomRepository.save(room);
    }
    public void deleteRoomById(Long hotelId, Long id){
        Hotel hotel = hotelRepository.findById(id).
                orElseThrow(()-> new HotelNotFoundException("hotel não foi encontrado"));
        Room room = roomRepository.findById(id).
                orElseThrow(()-> new QuartoNotFoundException("quarto não encontrado"));
        roomRepository.deleteById(id);

    }
}