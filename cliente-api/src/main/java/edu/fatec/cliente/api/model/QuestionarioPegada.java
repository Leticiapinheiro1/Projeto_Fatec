package edu.fatec.cliente.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="QuestionarioPegada")

public class QuestionarioPegada {
	@Id
	@GeneratedValue
	private Integer id;
	private String data;
	private Integer valorPegada;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getValorPegada() {
		return valorPegada;
	}
	public void setValorPegada(Integer valorPegada) {
		this.valorPegada = valorPegada;
	}


}
