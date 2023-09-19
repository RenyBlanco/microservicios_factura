package com.rbsoluciones.tienda.servicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbsoluciones.tienda.servicio.entity.Factura;

public interface IFacturaRepositorio extends JpaRepository<Factura, Long>{
	
	List<Factura> findByClienteId(Long clienteId);
	Factura findByNumeroFactura(String numeroFactura);

}
