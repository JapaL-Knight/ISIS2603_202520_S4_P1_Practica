package co.edu.uniandes.dse.parcial1.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class UbicacionBodegaEntity extends BaseEntity{
    
    @PodamExclude
    @OneToMany (mappedBy = "bodega")
    List<MercanciaEntity> mercancias;
    public Long numEstante;
    public String canasta;
    public Long pesoMaximo;
    
}
