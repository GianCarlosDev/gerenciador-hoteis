package br.com.gerenciamentohoteis.gerenciador.Service;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.CreateReservaDto;
import br.com.gerenciamentohoteis.gerenciador.Dto.Response.ListReservasByClientDto;
import br.com.gerenciamentohoteis.gerenciador.Entity.Hotel;
import br.com.gerenciamentohoteis.gerenciador.Entity.Quarto;
import br.com.gerenciamentohoteis.gerenciador.Entity.Reserva;
import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.QuartoNotFoundException;
import br.com.gerenciamentohoteis.gerenciador.Repository.HotelRepository;
import br.com.gerenciamentohoteis.gerenciador.Repository.QuartoRepository;
import br.com.gerenciamentohoteis.gerenciador.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ReservaService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private QuartoRepository quartoRepository;
    @Autowired
    private ReservaRepository reservaRepository;

    public CreateReservaDto reservando(String nomeHotel,Integer numero, CreateReservaDto createReservaDto){
        Quarto quarto = quartoRepository.findByNumeroAndNomeHotel(numero,nomeHotel)
                .orElseThrow(()-> new QuartoNotFoundException("quarto não encontrado"));
        if(!quarto.getDisponivel()){
            throw new QuartoNotFoundException("quarto reservado para essa data");
        }
        Reserva reserva = new Reserva();
        reserva.setNomeCliente(createReservaDto.getNomeCliente());
        reserva.setCheckin(createReservaDto.getCheckin());
        reserva.setCheckout(createReservaDto.getCheckout());
        reserva.setQuarto(quarto);
        reserva.setCpf(createReservaDto.getCpf());
        reservaRepository.save(reserva);
        quarto.setDisponivel(false);
        quartoRepository.save(quarto);
        return createReservaDto;
    }
    public Stream<ListReservasByClientDto> consultandoReservas(String cpf){
        List<Reserva> reservas = reservaRepository.findByCpf(cpf);
        return reservas.stream().map(
                entity -> {
                    Quarto quarto = entity.getQuarto();
                    Hotel hotel = quarto.getHotel();
                          return new ListReservasByClientDto(
                               entity.getNomeCliente(),
                                  quarto.getNumero(),
                                  quarto.getPrecoNoite(),
                                  hotel.getNomeHotel(),
                                  entity.getCheckin(),
                                  entity.getCheckout()
                            );
                });
    }
}

