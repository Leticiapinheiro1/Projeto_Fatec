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
import edu.fatec.cliente.api.dao.RespostaDao;
import edu.fatec.cliente.api.model.Resposta;

@RequestMapping("/Resposta")
@RestController
@SpringBootApplication
@CrossOrigin (origins = "*")
public class RespostaController {
	
	  private RespostaDao repository;

	   RespostaController(RespostaDao respostadao) {
	       this.repository = respostadao;
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
	   public Resposta create(@RequestBody Resposta resposta){
	      return repository.save(resposta);
	   }

		/*
		 * @PutMapping(value="/{id}") public ResponseEntity update(@PathVariable("id")
		 * int id,
		 * 
		 * @RequestBody Resposta resposta) { return repository.findById(id) .map(record
		 * -> { record.setDataResposta(Resposta.getDataResposta());
		 * record.setEstatisticaPegada(Resposta.getEstatisticaPegada());
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