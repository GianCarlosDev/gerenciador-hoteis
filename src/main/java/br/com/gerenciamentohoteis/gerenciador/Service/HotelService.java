package br.com.gerenciamentohoteis.gerenciador.Service;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.CreatedHotelDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Request.GetHotelByIdDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Request.UpdateHotelDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListHotelDTO;
import br.com.gerenciamentohoteis.gerenciador.Entity.Hotel;
import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.HotelNotFoundException;
import br.com.gerenciamentohoteis.gerenciador.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class HotelService {
    @Autowired
   private HotelRepository hotelRepository;

    public CreatedHotelDTO createdHotel(CreatedHotelDTO createdHotelDto){
        Hotel hotel = new Hotel();
        hotel.setNameHotel(createdHotelDto.getNameHotel());
        hotel.setAddress(createdHotelDto.getAddress());
        hotelRepository.save(hotel);
        return createdHotelDto;
    }

    public Stream<ListHotelDTO> listHotel(){
        List<Hotel> entity = hotelRepository.findAll();
        return entity.stream().map( hotel -> new ListHotelDTO(
                hotel.getHotelId(),hotel.getNameHotel(),hotel.getAddress()
        ));
    }

    public GetHotelByIdDTO getHotelById (Long id){
        Hotel entity = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Hotel não foi encontrado"));
        return new GetHotelByIdDTO(
                entity.getHotelId(),entity.getNameHotel(),entity.getAddress());
    }
    public void updateById (Long id, UpdateHotelDTO updateHotelDto){
        Hotel hotelEntity = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Hotel não foi encontrado"));
        hotelEntity.setNameHotel(updateHotelDto.getNomeHotel());
        hotelEntity.setAddress(updateHotelDto.getEndereco());
        hotelRepository.save(hotelEntity);
    }
    public void deleteHotelById (Long id){
        boolean idExists = hotelRepository.existsById(id);
        if (!idExists){
            throw new HotelNotFoundException("Hotel não existe");
        }
        hotelRepository.deleteById(id);
    }
}
