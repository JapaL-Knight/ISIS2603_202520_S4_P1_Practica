package co.edu.uniandes.dse.parcial1.services;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@DataJpaTest
@Transactional
@Import(UbicacionBodegaService.class)
public class UbicacionBodegaServiceTest {

    // TODO: Escriba las pruebas para la clase UbicacionBodegaService

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UbicacionBodegaService bodegaService;

    
    private final PodamFactory factory = new PodamFactoryImpl();

    private List<MercanciaEntity> mercanciaList = new ArrayList<>();

    private UbicacionBodegaEntity bodega = new UbicacionBodegaEntity();


    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from UbicacionBodegaEntity").executeUpdate();
    }

    private void insertData() {

        bodega = factory.manufacturePojo(UbicacionBodegaEntity.class);
        bodega.setMercancias(new ArrayList<>());
        entityManager.persist(bodega);
    }
    @Test
    void createUbicacionBodegaEntityTest() throws IllegalArgumentException{
        bodega.setNumEstante(1L);
        UbicacionBodegaEntity ubi = bodegaService.crearBodega(bodega);
        assertEquals(bodega, ubi);
    }
    @Test
    void createUbicacionBodegaEntityTestFailed() throws IllegalArgumentException{
        bodega.setNumEstante(-1L);
        assertThrows(IllegalArgumentException.class, () -> bodegaService.crearBodega(bodega));
    }   
}
