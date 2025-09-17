package co.edu.uniandes.dse.parcial1.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaService {
    
    // TODO: Cree la lógica de creación de una mercancía

    @Autowired
    private MercanciaRepository mercanciaRepository;

    public MercanciaEntity crearMercancia(MercanciaEntity mercancia) throws IllegalArgumentException{


        if(mercancia.getBarCorde().isBlank()){
            throw new IllegalArgumentException("bar code can't be blank:" + mercancia.getBarCorde() );
        }

        for(MercanciaEntity i: mercanciaRepository.findAll()){
            if(mercancia.getBarCorde().equals(i.getBarCorde()) && !i.equals(mercancia)){
                    throw new IllegalArgumentException("Bar code has to be unique");
                }
        }

        if(mercancia.getFechaResepcion().isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Reception date cant be before present date");
        }
        
        return mercanciaRepository.save(mercancia);
    }
}
