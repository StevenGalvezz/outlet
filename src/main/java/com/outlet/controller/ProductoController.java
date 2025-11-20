// ProductoController.java
package com.outlet.controller;

import com.outlet.domain.Producto;
import com.outlet.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    // HU11: Agregar producto
    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.agregarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }
    
    // HU11: Modificar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> modificarProducto(
            @PathVariable Long id, 
            @RequestBody Producto producto) {
        try {
            Producto productoActualizado = productoService.modificarProducto(id, producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // HU11: Eliminar producto (desactivar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
    
    // Obtener todos los productos activos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos() {
        List<Producto> productos = productoService.obtenerProductosActivos();
        return ResponseEntity.ok(productos);
    }
    
    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Buscar productos
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarProductos(@RequestParam String nombre) {
        List<Producto> productos = productoService.buscarProductos(nombre);
        return ResponseEntity.ok(productos);
    }
    
    // Productos con stock bajo
    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Producto>> productosStockBajo() {
        List<Producto> productos = productoService.obtenerProductosBajoStock();
        return ResponseEntity.ok(productos);
    }
}