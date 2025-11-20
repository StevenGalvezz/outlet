// InventarioService.java
package com.outlet.service;

import com.outlet.domain.MovimientoInventario;
import com.outlet.domain.Producto;
import com.outlet.repository.MovimientoInventarioRepository;
import com.outlet.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class InventarioService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private MovimientoInventarioRepository movimientoRepository;
    
    // HU12: Registrar entrada de inventario
    public MovimientoInventario registrarEntrada(Long productoId, Integer cantidad, String observaciones, String usuario) {
        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        Integer stockAnterior = producto.getStockActual();
        Integer stockNuevo = stockAnterior + cantidad;
        
        producto.setStockActual(stockNuevo);
        productoRepository.save(producto);
        
        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setProducto(producto);
        movimiento.setTipo(MovimientoInventario.TipoMovimiento.ENTRADA);
        movimiento.setCantidad(cantidad);
        movimiento.setStockAnterior(stockAnterior);
        movimiento.setStockNuevo(stockNuevo);
        movimiento.setObservaciones(observaciones);
        movimiento.setUsuarioRegistro(usuario);
        
        return movimientoRepository.save(movimiento);
    }
    
    // HU12: Registrar salida de inventario
    public MovimientoInventario registrarSalida(Long productoId, Integer cantidad, String observaciones, String usuario) {
        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        Integer stockAnterior = producto.getStockActual();
        
        if (stockAnterior < cantidad) {
            throw new RuntimeException("Stock insuficiente. Disponible: " + stockAnterior + ", Solicitado: " + cantidad);
        }
        
        Integer stockNuevo = stockAnterior - cantidad;
        
        producto.setStockActual(stockNuevo);
        productoRepository.save(producto);
        
        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setProducto(producto);
        movimiento.setTipo(MovimientoInventario.TipoMovimiento.SALIDA);
        movimiento.setCantidad(cantidad);
        movimiento.setStockAnterior(stockAnterior);
        movimiento.setStockNuevo(stockNuevo);
        movimiento.setObservaciones(observaciones);
        movimiento.setUsuarioRegistro(usuario);
        
        return movimientoRepository.save(movimiento);
    }
    
    // Obtener historial de movimientos de un producto
    public List<MovimientoInventario> obtenerHistorialProducto(Long productoId) {
        return movimientoRepository.findByProductoId(productoId);
    }
    
    // Obtener movimientos por rango de fechas
    public List<MovimientoInventario> obtenerMovimientosPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        return movimientoRepository.findByFechaMovimientoBetween(inicio, fin);
    }
    
    // Obtener todos los movimientos
    public List<MovimientoInventario> obtenerTodosMovimientos() {
        return movimientoRepository.findAll();
    }
}