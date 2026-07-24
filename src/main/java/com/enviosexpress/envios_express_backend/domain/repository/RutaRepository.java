package com.enviosexpress.envios_express_backend.domain.repository;

import com.enviosexpress.envios_express_backend.domain.model.EstadoRuta;
import com.enviosexpress.envios_express_backend.domain.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutaRepository extends JpaRepository<Ruta, Long> {

    List<Ruta> findByEstado(EstadoRuta estado);

    List<Ruta> findByDespachadorId(Long despachadorId);

    List<Ruta> findByVehiculoId(Long vehiculoId);
}
