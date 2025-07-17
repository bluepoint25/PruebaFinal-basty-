package com.ecomarket.producto.repository;

import com.ecomarket.producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    Optional<Producto> findByNombre(String nombre);
}