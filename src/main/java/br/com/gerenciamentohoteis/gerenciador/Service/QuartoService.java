package br.com.gerenciamentohoteis.gerenciador.Service;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.CreatedQuartoDto;
import br.com.gerenciamentohoteis.gerenciador.Dto.Request.GetQuartoByHotel;
import br.com.gerenciamentohoteis.gerenciador.Dto.Request.UpdateQuartoDto;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListQuartoDto;
import br.com.gerenciamentohoteis.gerenciador.Entity.Hotel;
import br.com.gerenciamentohoteis.gerenciador.Entity.Quarto;
import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.HotelNotFoundException;
import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.QuartoNotFoundException;
import br.com.gerenciamentohoteis.gerenciador.Repository.HotelRepository;
import br.com.gerenciamentohoteis.gerenciador.Repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public CreatedQuartoDto createdQuarto(Long hotelId,CreatedQuartoDto createdQuartoDto){
        Hotel hotel =  hotelRepository.findById(hotelId)
                .orElseThrow(()-> new HotelNotFoundException("Hotel não foi encontrado"));
        Quarto quarto = new Quarto();
        quarto.setHotel(hotel);
        quarto.setQuartos(createdQuartoDto.getQuartos());
        quarto.setPrecoNoite(createdQuartoDto.getPrecoNoite());
        quarto.setNumero(createdQuartoDto.getNumero());
        quartoRepository.save(quarto);
        return createdQuartoDto;
    }
    public Stream<ListQuartoDto> listQuartos (Long hotelId){
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new HotelNotFoundException("Hotel não foi encontrado"));
        List<Quarto> quartos = quartoRepository.findByHotelId(hotelId);
        return quartos.stream().map(entity -> new ListQuartoDto(
                entity.getQuartos(),entity.getNumero(),entity.getPrecoNoite(),entity.getDisponivel()
        ));
    }
    public GetQuartoByHotel quartoByHotel(Long hotelId,Long id){
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new HotelNotFoundException("hotel não foi encontrado"));
        Quarto quarto = quartoRepository.findById(id).orElseThrow(()-> new QuartoNotFoundException("quarto não encontrado"));

        return new GetQuartoByHotel(
                hotel.getNomeHotel(),quarto.getQuartos(),quarto.getPrecoNoite(),
                quarto.getDisponivel(),quarto.getNumero());
    }
    public void updateQuarto(Long hotelId, Long id, UpdateQuartoDto dto){
        Hotel hotel = hotelRepository.findById(id).
                orElseThrow(()-> new HotelNotFoundException("hotel não foi encontrado"));
        Quarto quarto = quartoRepository.findById(id).
                orElseThrow(()-> new QuartoNotFoundException("quarto não encontrado"));
        quarto.setQuartos(dto.quartos());
        quarto.setPrecoNoite(dto.precoNoite());
        quarto.setNumero(dto.numero());
        quartoRepository.save(quarto);
    }
    public void deleteQuartoById(Long hotelId,Long id){
        Hotel hotel = hotelRepository.findById(id).
                orElseThrow(()-> new HotelNotFoundException("hotel não foi encontrado"));
        Quarto quarto = quartoRepository.findById(id).
                orElseThrow(()-> new QuartoNotFoundException("quarto não encontrado"));
        quartoRepository.deleteById(id);

    }
}