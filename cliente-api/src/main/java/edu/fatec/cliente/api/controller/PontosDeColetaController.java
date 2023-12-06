package edu.fatec.cliente.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.fatec.cliente.api.dao.ClienteDao;
import edu.fatec.cliente.api.dao.PontosDeColetaDao;
import edu.fatec.cliente.api.model.PontosDeColeta;

@RequestMapping("/PontosDeColeta")
@RestController
@SpringBootApplication
@CrossOrigin (origins = "*")
public class PontosDeColetaController {
	
	  private PontosDeColetaDao repository;

	   PontosDeColetaController(PontosDeColetaDao pontosDeColetadao) {
	       this.repository = pontosDeColetadao;
	   }
	   
	   @GetMapping
	   public List findAll(){
	      return repository.findAll();
	   }
	   
	   @GetMapping(path = {"/{id}"})
	   public ResponseEntity findById(@PathVariable int id){
	      return repository.findById(id)
	              .map(record -> ResponseEntity.ok().body(record))
	              .orElse(ResponseEntity.notFound().build());
	   }
	   
	   @PostMapping
	   public PontosDeColeta create(@RequestBody PontosDeColeta pontosDeColeta){
	      return repository.save(pontosDeColeta);
	   }
	   	   	   
		/*
		 * @PutMapping(value="/{id}") public ResponseEntity update(@PathVariable("id")
		 * int id,
		 * 
		 * @RequestBody PontosDeColeta pontosDeColeta) { return repository.findById(id)
		 * .map(record -> { record.setEndereco(pontosDeColeta.getEndereco());
		 * 
		 * 
		 * }).orElse(ResponseEntity.notFound().build()); }
		 */
	   
	   @DeleteMapping(path ={"/{id}"})
	   public ResponseEntity <?> delete(@PathVariable int id) {
	      return repository.findById((int)id)
	              .map(record -> {
	                  repository.deleteById((int) id);
	                  return ResponseEntity.ok().build();
	              }).orElse(ResponseEntity.notFound().build());
	   }
}