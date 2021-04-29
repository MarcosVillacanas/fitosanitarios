package com.ministerio.fitosanitarios2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ministerio.fitosanitarios2.modelos.Especie;

public interface EspecieRepositorio extends JpaRepository<Especie, Long> {

}