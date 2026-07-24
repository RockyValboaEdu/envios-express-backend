package com.enviosexpress.envios_express_backend.impl;


import com.enviosexpress.envios_express_backend.domain.model.EstadoVehiculo;
import com.enviosexpress.envios_express_backend.domain.model.Usuario;
import com.enviosexpress.envios_express_backend.domain.model.Vehiculo;
import com.enviosexpress.envios_express_backend.domain.repository.UsuarioRepository;
import com.enviosexpress.envios_express_backend.domain.repository.VehiculoRepository;
import com.enviosexpress.envios_express_backend.domain.service.VehiculoService;
import com.enviosexpress.envios_express_backend.infrastructure.exception.BusinessException;
import com.enviosexpress.envios_express_backend.infrastructure.exception.ResourceNotFoundException;
import com.enviosexpress.envios_express_backend.web.dto.request.VehiculoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Vehiculo crear(VehiculoRequest request) {
        if (vehiculoRepository.existsByPlaca(request.getPlaca())) {
            throw new BusinessException("Ya existe un vehiculo registrado con esa placa");
        }

        Vehiculo vehiculo = Vehiculo.builder()
                .placa(request.getPlaca())
                .tipo(request.getTipo())
                .capacidadKg(request.getCapacidadKg())
                .conductor(resolverConductor(request.getConductorId()))
                .estado(EstadoVehiculo.DISPONIBLE)
                .build();

        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo actualizar(Long id, VehiculoRequest request) {
        Vehiculo vehiculo = obtenerPorId(id);

        vehiculo.setPlaca(request.getPlaca());
        vehiculo.setTipo(request.getTipo());
        vehiculo.setCapacidadKg(request.getCapacidadKg());
        vehiculo.setConductor(resolverConductor(request.getConductorId()));

        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo obtenerPorId(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehiculo no encontrado con id: " + id));
    }

    @Override
    public List<Vehiculo> listarTodos() {
        return vehiculoRepository.findAll();
    }

    @Override
    public List<Vehiculo> listarPorEstado(EstadoVehiculo estado) {
        return vehiculoRepository.findByEstado(estado);
    }

    @Override
    public Vehiculo cambiarEstado(Long id, EstadoVehiculo nuevoEstado) {
        Vehiculo vehiculo = obtenerPorId(id);
        vehiculo.setEstado(nuevoEstado);
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public void eliminar(Long id) {
        Vehiculo vehiculo = obtenerPorId(id);
        vehiculoRepository.delete(vehiculo);
    }

    private Usuario resolverConductor(Long conductorId) {
        if (conductorId == null) {
            return null;
        }
        return usuarioRepository.findById(conductorId)
                .orElseThrow(() -> new ResourceNotFoundException("Conductor no encontrado con id: " + conductorId));
    }
}
