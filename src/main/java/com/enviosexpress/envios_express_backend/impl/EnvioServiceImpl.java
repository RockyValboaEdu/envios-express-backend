package com.enviosexpress.envios_express_backend.impl;

import com.enviosexpress.envios_express_backend.domain.model.Envio;
import com.enviosexpress.envios_express_backend.domain.model.EstadoEnvio;
import com.enviosexpress.envios_express_backend.domain.model.Ruta;
import com.enviosexpress.envios_express_backend.domain.model.Usuario;
import com.enviosexpress.envios_express_backend.domain.repository.EnvioRepository;
import com.enviosexpress.envios_express_backend.domain.repository.RutaRepository;
import com.enviosexpress.envios_express_backend.domain.repository.UsuarioRepository;
import com.enviosexpress.envios_express_backend.domain.service.EnvioService;
import com.enviosexpress.envios_express_backend.infrastructure.exception.ResourceNotFoundException;
import com.enviosexpress.envios_express_backend.web.dto.request.EnvioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvioServiceImpl implements EnvioService {

    private final EnvioRepository envioRepository;
    private final UsuarioRepository usuarioRepository;
    private final RutaRepository rutaRepository;

    @Override
    public Envio crear(EnvioRequest request) {
        Usuario cliente = usuarioRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente no encontrado con id: " + request.getClienteId()));

        Ruta ruta = null;
        if (request.getRutaId() != null) {
            ruta = rutaRepository.findById(request.getRutaId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Ruta no encontrada con id: " + request.getRutaId()));
        }

        Envio envio = Envio.builder()
                .cliente(cliente)
                .ruta(ruta)
                .direccionEntrega(request.getDireccionEntrega())
                .destinatarioNombre(request.getDestinatarioNombre())
                .destinatarioTelefono(request.getDestinatarioTelefono())
                .pesoKg(request.getPesoKg())
                .estado(ruta != null ? EstadoEnvio.ASIGNADO : EstadoEnvio.PENDIENTE)
                .build();

        return envioRepository.save(envio);
    }

    @Override
    public Envio obtenerPorId(Long id) {
        return envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envio no encontrado con id: " + id));
    }

    @Override
    public Envio obtenerPorCodigoSeguimiento(String codigo) {
        return envioRepository.findByCodigoSeguimiento(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Envio no encontrado con codigo: " + codigo));
    }

    @Override
    public List<Envio> listarTodos() {
        return envioRepository.findAll();
    }

    @Override
    public List<Envio> listarPorCliente(Long clienteId) {
        return envioRepository.findByClienteId(clienteId);
    }

    @Override
    public List<Envio> listarPorRuta(Long rutaId) {
        return envioRepository.findByRutaId(rutaId);
    }
}
