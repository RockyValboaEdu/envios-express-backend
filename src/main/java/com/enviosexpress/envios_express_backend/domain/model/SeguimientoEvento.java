package com.enviosexpress.envios_express_backend.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Registra cada evento de cambio de estado o ubicacion de un envio,
 * para construir el historial de trazabilidad (tracking).
 */
@Entity
@Table(name = "seguimiento_eventos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "envio_id", nullable = false)
    private Envio envio;

    @Enumerated(EnumType.STRING)
    private EstadoEnvio estado;

    private Double latitud;

    private Double longitud;

    private String comentario;

    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}
