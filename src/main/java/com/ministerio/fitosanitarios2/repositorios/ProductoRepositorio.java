package com.ministerio.fitosanitarios2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ministerio.fitosanitarios2.modelos.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

}