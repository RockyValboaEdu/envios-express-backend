package com.enviosexpress.envios_express_backend.domain.repository;

import com.enviosexpress.envios_express_backend.domain.model.EstadoEnvio;
import com.enviosexpress.envios_express_backend.domain.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnvioRepository extends JpaRepository<Envio, Long> {

    Optional<Envio> findByCodigoSeguimiento(String codigoSeguimiento);

    List<Envio> findByClienteId(Long clienteId);

    List<Envio> findByRutaId(Long rutaId);

    List<Envio> findByEstado(EstadoEnvio estado);
}
