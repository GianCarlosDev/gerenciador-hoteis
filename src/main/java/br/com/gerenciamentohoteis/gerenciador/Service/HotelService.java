package br.com.gerenciamentohoteis.gerenciador.Service;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.CreatedHotelDto;
import br.com.gerenciamentohoteis.gerenciador.Dto.Request.GetById;
import br.com.gerenciamentohoteis.gerenciador.Dto.Request.UpdateHotelDto;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListHotelDto;
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

    public CreatedHotelDto createdHotel(CreatedHotelDto createdHotelDto){
        Hotel hotel = new Hotel();
        hotel.setNomeHotel(createdHotelDto.getNomeHotel());
        hotel.setEndereco(createdHotelDto.getEndereco());
        hotelRepository.save(hotel);
        return createdHotelDto;
    }

    public Stream<ListHotelDto> listaDeHoteis(){
        List<Hotel> entity = hotelRepository.findAll();
        return entity.stream().map( hotel -> new ListHotelDto(
                hotel.getId(),hotel.getNomeHotel(),hotel.getEndereco()
        ));
    }

    public GetById getHotelById (Long id){
        Hotel entity = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Hotel não foi encontrado"));
        return new GetById(
                entity.getId(),entity.getNomeHotel(),entity.getEndereco());
    }
    public void updateById (Long id, UpdateHotelDto updateHotelDto){
        Hotel hotelEntity = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Hotel não foi encontrado"));
        hotelEntity.setNomeHotel(updateHotelDto.getNomeHotel());
        hotelEntity.setEndereco(updateHotelDto.getEndereco());
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
