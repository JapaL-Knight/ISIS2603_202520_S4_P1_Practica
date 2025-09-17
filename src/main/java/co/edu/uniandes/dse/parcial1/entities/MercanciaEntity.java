package co.edu.uniandes.dse.parcial1.entities;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class MercanciaEntity extends BaseEntity{
   
    @ManyToOne
    public UbicacionBodegaEntity bodega;
    public String nombre;
    public String barCorde;
    public LocalDateTime fechaResepcion;
    public Long cantidadDisponible;


}
