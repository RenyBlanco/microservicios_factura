package com.rbsoluciones.tienda.servicio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbsoluciones.tienda.servicio.entity.Factura;
import com.rbsoluciones.tienda.servicio.servicio.IFacturaServicio;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class FacturaController {

	@Autowired
	IFacturaServicio fs;
	
	@GetMapping("/facturas")
	public ResponseEntity<List<Factura>> listaFacturas(){
		List<Factura> facturas = fs.listaFacturas();
		if(facturas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(facturas);
	}
	
	@GetMapping("/factura/{id}")
	public ResponseEntity<Factura> listaFactura(@PathVariable("id") Long id){
		log.info("Recuperando factura con id {}", id);
		Factura fDB = fs.listaUnaFact(id);
		if(fDB==null) {
			log.error("Factura con id {} no encontrado",id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(fDB);
	}
	
	@PostMapping("/factura/crear")
	public ResponseEntity<Factura> creaFactura(@Valid @RequestBody Factura f, BindingResult result){
		log.info("Creando factura : {}",f);
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
		}
		Factura fDB = fs.creaFactura(f);
		return ResponseEntity.status(HttpStatus.CREATED).body(fDB);
	}
	
	@PutMapping("/factura/{id}")
	public ResponseEntity<Factura> actualizaFact(@PathVariable("id") Long id, @RequestBody Factura f){
		log.info("Modificando factura id {}",id);
		f.setId(id);
		Factura cF = fs.actualizaFactura(f);
		if(cF==null) {
			log.error("No se actualizó, Factura con id {} no encontrada",id);
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cF);
	}
	
	@DeleteMapping("/factura/{id}")
	public ResponseEntity<Factura> eliminaFact(@PathVariable("id") Long id){
		log.info("Eliminando factura id {}",id);
		Factura cF = fs.listaUnaFact(id);
		if(cF==null) {
			log.error("No se elimino, Factura con id {} no encontrada",id);
			return ResponseEntity.notFound().build();
		}
		cF = fs.eliminaFactura(cF);
		return ResponseEntity.ok(cF);
	}
	
	private String formatMessage(BindingResult result){
        List<Map<String,String>> errores = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errores).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
