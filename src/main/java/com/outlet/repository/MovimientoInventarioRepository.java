// MovimientoInventarioRepository.java
package com.outlet.repository;

import com.outlet.domain.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
    
    List<MovimientoInventario> findByProductoId(Long productoId);
    
    @Query("SELECT m FROM MovimientoInventario m WHERE m.fechaMovimiento BETWEEN :inicio AND :fin")
    List<MovimientoInventario> findByFechaMovimientoBetween(LocalDateTime inicio, LocalDateTime fin);
    
    List<MovimientoInventario> findByTipo(MovimientoInventario.TipoMovimiento tipo);
}