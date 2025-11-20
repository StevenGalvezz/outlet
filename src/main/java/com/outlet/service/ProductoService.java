package com.outlet.service;

import com.outlet.domain.Categoria;
import com.outlet.domain.Producto;
import com.outlet.repository.CategoriaRepository;
import com.outlet.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    // HU11: Agregar producto
    public Producto agregarProducto(Producto producto) {
        if (producto.getCategoria() != null && producto.getCategoria().getId_categoria() != null) {
            Optional<Categoria> categoria = categoriaRepository.findById(producto.getCategoria().getId_categoria());
            categoria.ifPresent(producto::setCategoria);
        }
        return productoRepository.save(producto);
    }
    
    // HU11: Modificar producto
    public Producto modificarProducto(Long id, Producto productoActualizado) {
        return productoRepository.findById(id)
            .map(producto -> {
                producto.setNombre(productoActualizado.getNombre());
                producto.setDescripcion(productoActualizado.getDescripcion());
                producto.setPrecio(productoActualizado.getPrecio());
                producto.setStockMinimo(productoActualizado.getStockMinimo());
                producto.setImagenUrl(productoActualizado.getImagenUrl());
                producto.setActivo(productoActualizado.getActivo());
                
                if (productoActualizado.getCategoria() != null) {
                    producto.setCategoria(productoActualizado.getCategoria());
                }
                
                return productoRepository.save(producto);
            })
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }
    
    // HU11: Eliminar producto (desactivar)
    public void eliminarProducto(Long id) {
        productoRepository.findById(id)
            .ifPresent(producto -> {
                producto.setActivo(false);
                productoRepository.save(producto);
            });
    }
    
    // HU11: Eliminar permanentemente
    public void eliminarProductoPermanente(Long id) {
        productoRepository.deleteById(id);
    }
    
    // Obtener todos los productos activos
    public List<Producto> obtenerProductosActivos() {
        return productoRepository.findByActivoTrue();
    }
    
    // Obtener producto por ID
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }
    
    // Obtener productos por categoría - CAMBIO AQUÍ: Long → Integer
    public List<Producto> obtenerProductosPorCategoria(Integer categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
    
    // Buscar productos
    public List<Producto> buscarProductos(String nombre) {
        return productoRepository.buscarPorNombre(nombre);
    }
    
    // Productos con stock bajo
    public List<Producto> obtenerProductosBajoStock() {
        return productoRepository.findProductosBajoStock();
    }
}