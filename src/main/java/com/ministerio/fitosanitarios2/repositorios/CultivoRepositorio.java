package com.ministerio.fitosanitarios2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ministerio.fitosanitarios2.modelos.Cultivo;

public interface CultivoRepositorio extends JpaRepository<Cultivo, Long> {

}