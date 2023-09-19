package com.rbsoluciones.tienda.servicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbsoluciones.tienda.servicio.entity.FacturaDetalle;

public interface IFacturaDetalleRepositorio extends JpaRepository<FacturaDetalle, Long>{

}
