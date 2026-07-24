package com.enviosexpress.envios_express_backend.domain.service;

import com.enviosexpress.envios_express_backend.domain.model.EstadoVehiculo;
import com.enviosexpress.envios_express_backend.domain.model.Vehiculo;
import com.enviosexpress.envios_express_backend.web.dto.request.VehiculoRequest;

import java.util.List;

public interface VehiculoService {

    Vehiculo crear(VehiculoRequest request);

    Vehiculo actualizar(Long id, VehiculoRequest request);

    Vehiculo obtenerPorId(Long id);

    List<Vehiculo> listarTodos();

    List<Vehiculo> listarPorEstado(EstadoVehiculo estado);

    Vehiculo cambiarEstado(Long id, EstadoVehiculo nuevoEstado);

    void eliminar(Long id);
}
