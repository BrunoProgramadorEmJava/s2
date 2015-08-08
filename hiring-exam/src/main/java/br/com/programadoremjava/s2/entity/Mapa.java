package br.com.programadoremjava.s2.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mapa")
public class Mapa implements Serializable {

	private static final long serialVersionUID = -4495146457404504024L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mapa_id")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@OneToMany(targetEntity = Segmento.class, mappedBy = "mapa")
	private List<Segmento> segmentos;

	public Mapa() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mapa other = (Mapa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public List<Segmento> getSegmentos() {
		return segmentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSegmentos(List<Segmento> segmentos) {
		this.segmentos = segmentos;
	}

}
