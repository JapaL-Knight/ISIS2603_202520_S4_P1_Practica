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
@Import(MercanciaService.class)
public class MercanciaServiceTest {
    
    // TODO: Escriba las pruebas para la clase MercanciaService

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MercanciaService mercanciaService;
    
    private final PodamFactory factory = new PodamFactoryImpl();

    private MercanciaEntity mercancia1 = new MercanciaEntity();
    private MercanciaEntity mercancia2 = new MercanciaEntity();


    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from MercanciaEntity").executeUpdate();
    }

    private void insertData() {
        mercancia1 = factory.manufacturePojo(MercanciaEntity.class);
        mercancia1.setBodega(null);
        entityManager.persist(mercancia1);
        mercancia2 = factory.manufacturePojo(MercanciaEntity.class);
        mercancia2.setBodega(null);
        entityManager.persist(mercancia2);
        
    }

    @Test 
    void crearMercanciaTest() throws IllegalArgumentException{
        mercancia1.setBarCorde("1");
        mercancia1.setFechaResepcion(LocalDateTime.now().minusDays(2));
    
        MercanciaEntity merch = mercanciaService.crearMercancia(mercancia1);
        assertEquals(mercancia1, merch);
    }

    @Test 
    void crearMercanciaTestInvalid() throws IllegalArgumentException{
        
        LocalDateTime tiempo =LocalDateTime.now().minusDays(2);

        mercancia1.setBarCorde("1");
        mercancia2.setBarCorde("2");
        mercancia1.setFechaResepcion(tiempo);
        mercancia2.setFechaResepcion(tiempo);
        mercanciaService.crearMercancia(mercancia1);
        mercancia2.setBarCorde("1");
        assertThrows(IllegalArgumentException.class, () -> mercanciaService.crearMercancia(mercancia2));

    }
    
    
}
