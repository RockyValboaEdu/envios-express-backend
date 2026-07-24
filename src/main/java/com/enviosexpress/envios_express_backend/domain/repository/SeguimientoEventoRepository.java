package com.enviosexpress.envios_express_backend.domain.repository;

import com.enviosexpress.envios_express_backend.domain.model.SeguimientoEvento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeguimientoEventoRepository extends JpaRepository<SeguimientoEvento, Long> {

    List<SeguimientoEvento> findByEnvioIdOrderByTimestampAsc(Long envioId);
}
