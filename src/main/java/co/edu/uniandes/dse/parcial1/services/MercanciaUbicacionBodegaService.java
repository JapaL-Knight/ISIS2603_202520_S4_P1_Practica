package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaUbicacionBodegaService {

    // TODO: Cree la lógica de asociación entre mercancía y ubicación de bodega

    @Autowired
    private MercanciaRepository mRepo;
    @Autowired
    private UbicacionBodegaRepository ubiRepo;


    public MercanciaEntity addUbicacion(Long idMercancia, long idBodega){
        Optional<MercanciaEntity> mercancia = mRepo.findById(idMercancia);
        Optional<UbicacionBodegaEntity> bodega = ubiRepo.findById(idBodega);
        
        if(mercancia.isEmpty() || bodega.isEmpty()){
            throw new EntityNotFoundException("no se encontro la bodega con la Id dada o la mercancia con la Id dada");
        }

        mercancia.get().setBodega(bodega.get());
        bodega.get().getMercancias().add(mercancia.get());
        ubiRepo.save(bodega.get());
        return mRepo.save(mercancia.get());


    }

}
