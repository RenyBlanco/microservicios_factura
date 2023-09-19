package com.rbsoluciones.tienda.servicio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
@Entity
@Table(name="detalles_facturas")
public class FacturaDetalle {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Positive(message="La cantidad debe ser mayor que cero")
	private int cantidad;
	
	private Double precio;
	
	@Column(name="id_producto")
	private Long idProducto;
	
	@Transient
	private Double subtotal;
	
	public Double getSubTotal() {
		if(this.precio > 0 && this.cantidad>0) {
			return this.cantidad*this.precio;
		}else {
			return (double) 0;
		}
	}
	
	public FacturaDetalle() {
		this.cantidad=0;
		this.precio=(double)0;
	}
}
