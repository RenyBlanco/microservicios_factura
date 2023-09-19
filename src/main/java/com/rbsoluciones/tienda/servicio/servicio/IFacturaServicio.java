package com.rbsoluciones.tienda.servicio.servicio;

import java.util.List;

import com.rbsoluciones.tienda.servicio.entity.Factura;

public interface IFacturaServicio {
	
	public List<Factura> listaFacturas();
	public Factura creaFactura(Factura f);
	public Factura actualizaFactura(Factura f);
	public Factura listaUnaFact(Long id);
	public Factura eliminaFactura(Factura f);

}
