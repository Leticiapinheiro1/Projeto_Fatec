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
import edu.fatec.cliente.api.dao.UsuariosDao;
import edu.fatec.cliente.api.model.Usuarios;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@RequestMapping("/usuarios")
@RestController
@SpringBootApplication
@CrossOrigin (origins = "*")
public class UsuariosController {
	
	  private UsuariosDao repository;
	  @PersistenceContext
	  private EntityManager em;

	   UsuariosController(UsuariosDao usuariosdao) {
	       this.repository = usuariosdao;
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
	   public Usuarios create(@RequestBody Usuarios usuarios){
	      return repository.save(usuarios);
	   }
	   	   	   
	   @PutMapping(value="/{id}")
	   public ResponseEntity update(@PathVariable("id") int id,
	                                         @RequestBody Usuarios usuarios) {
	      return repository.findById(id)
	              .map(record -> {
	                  record.setEmail(usuarios.getEmail());
	                  record.setEndereco(usuarios.getEndereco());
	                  record.setTelefone(usuarios.getTelefone());
					  record.setPontuacao(usuarios.getPontuacao());
	                  record.setSenha(usuarios.getSenha());
	                  Usuarios updated = repository.save(record);
	                  return ResponseEntity.ok().body(updated);
	              }).orElse(ResponseEntity.notFound().build());
	   }

	@PostMapping(path = {"/login"})
	public ResponseEntity<Usuarios> findByNome(@RequestBody Usuarios usuario) {
		String query = "SELECT u FROM Usuarios u WHERE u.cpf = :cpf AND u.senha = :senha";

		TypedQuery<Usuarios> typedQuery = em.createQuery(query, Usuarios.class);
		typedQuery.setParameter("cpf", usuario.getCpf());
		typedQuery.setParameter("senha", usuario.getSenha());
		typedQuery.setMaxResults(1); // Limit the result to 1

		List<Usuarios> resultList = typedQuery.getResultList();
		if (!resultList.isEmpty()) {
			return ResponseEntity.ok().body(resultList.get(0));
		} else {
			return ResponseEntity.notFound().build();
		}
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