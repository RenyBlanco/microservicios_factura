package com.rbsoluciones.tienda.servicio.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="facturas")
public class Factura {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="numero_factura")
	private String numeroFactura;
	
	@Column(name="id_cliente")
	private Long clienteId;
	
	private String descripcion;
	
	@Column(name="created_at")
	@Temporal(TemporalType.DATE)
	private Date creado;
	
	private String estado;
	
	@Valid
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="id_factura")
	private List<FacturaDetalle> detalles;

	public Factura(){
		detalles = new ArrayList<>();
	}
	
	@PrePersist
	public void prePersist() {
		this.creado = new Date();
	}
}
