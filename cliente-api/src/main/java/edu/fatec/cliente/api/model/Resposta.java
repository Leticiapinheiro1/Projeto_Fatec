package edu.fatec.cliente.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Resposta")

public class Resposta {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer dataResposta;
	private Integer estatisticaPegada;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDataResposta() {
		return dataResposta;
	}
	public void setDataResposta(Integer dataResposta) {
		this.dataResposta = dataResposta;
	}
	public Integer getEstatisticaPegada() {
		return estatisticaPegada;
	}
	public void setEstatisticaPegada(Integer estatisticaPegada) {
		this.estatisticaPegada = estatisticaPegada;
	}

	

}
