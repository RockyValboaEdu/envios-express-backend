package com.enviosexpress.envios_express_backend.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "envios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigoSeguimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;

    @Column(nullable = false)
    private String direccionEntrega;

    private String destinatarioNombre;

    private String destinatarioTelefono;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EstadoEnvio estado = EstadoEnvio.PENDIENTE;

    private Double pesoKg;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaEntrega;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        if (this.codigoSeguimiento == null) {
            this.codigoSeguimiento = "EE-" + System.currentTimeMillis();
        }
    }
}
