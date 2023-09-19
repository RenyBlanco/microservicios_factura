package com.rbsoluciones.tienda.servicio.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbsoluciones.tienda.servicio.entity.Factura;
import com.rbsoluciones.tienda.servicio.repository.IFacturaDetalleRepositorio;
import com.rbsoluciones.tienda.servicio.repository.IFacturaRepositorio;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FacturaServicioImp implements IFacturaServicio {

	@Autowired
	IFacturaRepositorio fr;
	
	@Autowired
	IFacturaDetalleRepositorio fdr;
	
	@Override
	public List<Factura> listaFacturas() {
		return fr.findAll();
	}

	@Override
	public Factura creaFactura(Factura f) {
		Factura fDB = fr.findByNumeroFactura(f.getNumeroFactura());
		if(fDB != null) {
			return fDB;
		}
		f.setEstado("creada");
		return fr.save(f);
	}

	@Override
	public Factura actualizaFactura(Factura f) {
		Factura fDB = listaUnaFact(f.getId());
		if(fDB==null) {
			return null;
		}
		
		return fr.save(f);
	}

	@Override
	public Factura listaUnaFact(Long id) {

		return fr.findById(id).orElse(null);
	}

	@Override
	public Factura eliminaFactura(Factura f) {
		Factura fDB = listaUnaFact(f.getId());
		if(fDB==null) {
			return null;
		}
		fDB.setEstado("eliminado");
		return fr.save(fDB);
	}

}
