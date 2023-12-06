package edu.fatec.cliente.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Beneficios")

public class Beneficios {
	@Id
	@GeneratedValue
	private Integer id;
	private String descricao;
	private Integer quantidade_pontos;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getQuantidade_pontos() {
		return quantidade_pontos;
	}
	public void setQuantidade_pontos(Integer quantidade_pontos) {
		this.quantidade_pontos = quantidade_pontos;
	}

	

}
