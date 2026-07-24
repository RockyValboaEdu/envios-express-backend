package com.enviosexpress.envios_express_backend.impl;


import com.enviosexpress.envios_express_backend.domain.model.EstadoRuta;
import com.enviosexpress.envios_express_backend.domain.model.EstadoVehiculo;
import com.enviosexpress.envios_express_backend.domain.model.Ruta;
import com.enviosexpress.envios_express_backend.domain.model.Usuario;
import com.enviosexpress.envios_express_backend.domain.model.Vehiculo;
import com.enviosexpress.envios_express_backend.domain.repository.RutaRepository;
import com.enviosexpress.envios_express_backend.domain.repository.UsuarioRepository;
import com.enviosexpress.envios_express_backend.domain.repository.VehiculoRepository;
import com.enviosexpress.envios_express_backend.domain.service.RutaService;
import com.enviosexpress.envios_express_backend.infrastructure.exception.BusinessException;
import com.enviosexpress.envios_express_backend.infrastructure.exception.ResourceNotFoundException;
import com.enviosexpress.envios_express_backend.web.dto.request.RutaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RutaServiceImpl implements RutaService {

    private final RutaRepository rutaRepository;
    private final VehiculoRepository vehiculoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Ruta crear(RutaRequest request) {
        Usuario despachador = usuarioRepository.findById(request.getDespachadorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Despachador no encontrado con id: " + request.getDespachadorId()));

        Vehiculo vehiculo = null;
        if (request.getVehiculoId() != null) {
            vehiculo = obtenerVehiculoDisponible(request.getVehiculoId());
        }

        Ruta ruta = Ruta.builder()
                .nombre(request.getNombre())
                .origen(request.getOrigen())
                .destino(request.getDestino())
                .vehiculo(vehiculo)
                .despachador(despachador)
                .estado(EstadoRuta.PLANIFICADA)
                .fechaProgramada(request.getFechaProgramada())
                .build();

        if (vehiculo != null) {
            vehiculo.setEstado(EstadoVehiculo.EN_RUTA);
            vehiculoRepository.save(vehiculo);
        }

        return rutaRepository.save(ruta);
    }

    @Override
    public Ruta obtenerPorId(Long id) {
        return rutaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ruta no encontrada con id: " + id));
    }

    @Override
    public List<Ruta> listarTodas() {
        return rutaRepository.findAll();
    }

    @Override
    public List<Ruta> listarPorEstado(EstadoRuta estado) {
        return rutaRepository.findByEstado(estado);
    }

    @Override
    public Ruta asignarVehiculo(Long rutaId, Long vehiculoId) {
        Ruta ruta = obtenerPorId(rutaId);
        Vehiculo vehiculo = obtenerVehiculoDisponible(vehiculoId);

        ruta.setVehiculo(vehiculo);
        vehiculo.setEstado(EstadoVehiculo.EN_RUTA);
        vehiculoRepository.save(vehiculo);

        return rutaRepository.save(ruta);
    }

    @Override
    public Ruta cambiarEstado(Long id, EstadoRuta nuevoEstado) {
        Ruta ruta = obtenerPorId(id);
        ruta.setEstado(nuevoEstado);

        if ((nuevoEstado == EstadoRuta.FINALIZADA || nuevoEstado == EstadoRuta.CANCELADA)
                && ruta.getVehiculo() != null) {
            Vehiculo vehiculo = ruta.getVehiculo();
            vehiculo.setEstado(EstadoVehiculo.DISPONIBLE);
            vehiculoRepository.save(vehiculo);
        }

        return rutaRepository.save(ruta);
    }

    private Vehiculo obtenerVehiculoDisponible(Long vehiculoId) {
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehiculo no encontrado con id: " + vehiculoId));

        if (vehiculo.getEstado() != EstadoVehiculo.DISPONIBLE) {
            throw new BusinessException("El vehiculo con placa " + vehiculo.getPlaca() + " no esta disponible");
        }

        return vehiculo;
    }
}
