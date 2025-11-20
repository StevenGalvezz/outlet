// MovimientoInventario.java
package com.outlet.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario")
public class MovimientoInventario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoMovimiento tipo;
    
    @Column(nullable = false)
    private Integer cantidad;
    
    @Column(name = "stock_anterior", nullable = false)
    private Integer stockAnterior;
    
    @Column(name = "stock_nuevo", nullable = false)
    private Integer stockNuevo;
    
    @Column(length = 500)
    private String observaciones;
    
    @Column(name = "usuario_registro", length = 100)
    private String usuarioRegistro;
    
    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime fechaMovimiento;
    
    @PrePersist
    protected void onCreate() {
        fechaMovimiento = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    
    public TipoMovimiento getTipo() { return tipo; }
    public void setTipo(TipoMovimiento tipo) { this.tipo = tipo; }
    
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    
    public Integer getStockAnterior() { return stockAnterior; }
    public void setStockAnterior(Integer stockAnterior) { this.stockAnterior = stockAnterior; }
    
    public Integer getStockNuevo() { return stockNuevo; }
    public void setStockNuevo(Integer stockNuevo) { this.stockNuevo = stockNuevo; }
    
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    public String getUsuarioRegistro() { return usuarioRegistro; }
    public void setUsuarioRegistro(String usuarioRegistro) { this.usuarioRegistro = usuarioRegistro; }
    
    public LocalDateTime getFechaMovimiento() { return fechaMovimiento; }
    public void setFechaMovimiento(LocalDateTime fechaMovimiento) { this.fechaMovimiento = fechaMovimiento; }
    
    public enum TipoMovimiento {
        ENTRADA,
        SALIDA,
        AJUSTE,
        DEVOLUCION
    }
}