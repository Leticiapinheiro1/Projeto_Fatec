package edu.fatec.cliente.api.controller;

import java.util.List;

import edu.fatec.cliente.api.model.Usuarios;
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
import edu.fatec.cliente.api.dao.ParceirosDao;
import edu.fatec.cliente.api.model.Parceiros;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@RequestMapping("/parceiros")
@RestController
@SpringBootApplication
@CrossOrigin (origins = "*")
public class ParceirosController {
	
	  private ParceirosDao repository;
	  @PersistenceContext
	  private EntityManager em;

	   ParceirosController(ParceirosDao parceirosdao) {
	       this.repository = parceirosdao;
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
	   public Parceiros create(@RequestBody Parceiros parceiros){
	      return repository.save(parceiros);
	   }

	@PostMapping(path = {"/login"})
	public ResponseEntity<Parceiros> findByNome(@RequestBody Parceiros parceiro) {
		String query = "SELECT p FROM Parceiros p WHERE p.cnpj = :cnpj AND p.senha = :senha";

		TypedQuery<Parceiros> typedQuery = em.createQuery(query, Parceiros.class);
		typedQuery.setParameter("cnpj", parceiro.getCnpj());
		typedQuery.setParameter("senha", parceiro.getSenha());
		typedQuery.setMaxResults(1); // Limit the result to 1

		List<Parceiros> resultList = typedQuery.getResultList();
		if (!resultList.isEmpty()) {
			return ResponseEntity.ok().body(resultList.get(0));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	   	   	   
	   @PutMapping(value="/{id}")
	   public ResponseEntity update(@PathVariable("id") int id,
	                                         @RequestBody Parceiros parceiros) {
	      return repository.findById(id)
	              .map(record -> {
	                  record.setEmail(parceiros.getEmail());
	                  record.setEndereco(parceiros.getEndereco());
	                  record.setTelefone(parceiros.getTelefone());
	                  Parceiros updated = repository.save(record);
	                  return ResponseEntity.ok().body(updated);
	              }).orElse(ResponseEntity.notFound().build());
	   }
	   
	   @DeleteMapping(path ={"/{id}"})
	   public ResponseEntity <?> delete(@PathVariable int id) {
	      return repository.findById((int)id)
	              .map(record -> {
	                  repository.deleteById((int) id);
	                  return ResponseEntity.ok().build();
	              }).orElse(ResponseEntity.notFound().build());
	   }
}