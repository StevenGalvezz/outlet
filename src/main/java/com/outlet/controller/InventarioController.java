
package com.outlet.controller;

import com.outlet.domain.MovimientoInventario;
import com.outlet.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {
    
    @Autowired
    private InventarioService inventarioService;
    
    // HU12: Registrar entrada
    @PostMapping("/entrada")
    public ResponseEntity<MovimientoInventario> registrarEntrada(@RequestBody EntradaRequest request) {
        try {
            MovimientoInventario movimiento = inventarioService.registrarEntrada(
                request.getProductoId(),
                request.getCantidad(),
                request.getObservaciones(),
                request.getUsuario()
            );
            return new ResponseEntity<>(movimiento, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // HU12: Registrar salida
    @PostMapping("/salida")
    public ResponseEntity<MovimientoInventario> registrarSalida(@RequestBody SalidaRequest request) {
        try {
            MovimientoInventario movimiento = inventarioService.registrarSalida(
                request.getProductoId(),
                request.getCantidad(),
                request.getObservaciones(),
                request.getUsuario()
            );
            return new ResponseEntity<>(movimiento, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Obtener historial de un producto
    @GetMapping("/historial/{productoId}")
    public ResponseEntity<List<MovimientoInventario>> obtenerHistorial(@PathVariable Long productoId) {
        List<MovimientoInventario> movimientos = inventarioService.obtenerHistorialProducto(productoId);
        return ResponseEntity.ok(movimientos);
    }
    
    // Obtener movimientos por fecha
    @GetMapping("/movimientos")
    public ResponseEntity<List<MovimientoInventario>> obtenerMovimientosPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        List<MovimientoInventario> movimientos = inventarioService.obtenerMovimientosPorFecha(inicio, fin);
        return ResponseEntity.ok(movimientos);
    }
    
    // Obtener todos los movimientos
    @GetMapping("/movimientos/todos")
    public ResponseEntity<List<MovimientoInventario>> obtenerTodosMovimientos() {
        List<MovimientoInventario> movimientos = inventarioService.obtenerTodosMovimientos();
        return ResponseEntity.ok(movimientos);
    }
    
    // Clases internas para requests
    public static class EntradaRequest {
        private Long productoId;
        private Integer cantidad;
        private String observaciones;
        private String usuario;
        
        // Getters y Setters
        public Long getProductoId() { return productoId; }
        public void setProductoId(Long productoId) { this.productoId = productoId; }
        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
        public String getObservaciones() { return observaciones; }
        public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
        public String getUsuario() { return usuario; }
        public void setUsuario(String usuario) { this.usuario = usuario; }
    }
    
    public static class SalidaRequest {
        private Long productoId;
        private Integer cantidad;
        private String observaciones;
        private String usuario;
        
        // Getters y Setters
        public Long getProductoId() { return productoId; }
        public void setProductoId(Long productoId) { this.productoId = productoId; }
        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
        public String getObservaciones() { return observaciones; }
        public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
        public String getUsuario() { return usuario; }
        public void setUsuario(String usuario) { this.usuario = usuario; }
    }
}