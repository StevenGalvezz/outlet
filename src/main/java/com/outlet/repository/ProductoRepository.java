package com.outlet.repository;

import com.outlet.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    List<Producto> findByActivoTrue();
    
    // CAMBIO: categoriaId → categoria.id_categoria
    @Query("SELECT p FROM Producto p WHERE p.categoria.id_categoria = :categoriaId")
    List<Producto> findByCategoriaId(Integer categoriaId);
    
    @Query("SELECT p FROM Producto p WHERE p.stockActual <= p.stockMinimo")
    List<Producto> findProductosBajoStock();
    
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Producto> buscarPorNombre(String nombre);
}