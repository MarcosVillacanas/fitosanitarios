package com.ministerio.fitosanitarios2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ministerio.fitosanitarios2.modelos.Sustancia;

public interface SustanciaRepositorio extends JpaRepository<Sustancia, Long> {

}