package com.enviosexpress.envios_express_backend.domain.repository;

import com.enviosexpress.envios_express_backend.domain.model.EstadoVehiculo;
import com.enviosexpress.envios_express_backend.domain.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    Optional<Vehiculo> findByPlaca(String placa);

    List<Vehiculo> findByEstado(EstadoVehiculo estado);

    boolean existsByPlaca(String placa);
}
