package edu.fatec.cliente.api.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.fatec.cliente.api.model.PontosDeColeta;


@Repository
public interface PontosDeColetaDao extends JpaRepository<PontosDeColeta, Integer> {

	
}
