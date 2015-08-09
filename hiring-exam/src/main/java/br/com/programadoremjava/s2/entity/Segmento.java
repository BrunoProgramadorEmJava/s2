package br.com.programadoremjava.s2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "segmento")
public class Segmento implements Serializable {

	private static final long serialVersionUID = 1765904181625903628L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "segmento_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "mapa_id", referencedColumnName = "mapa_id")
	private Mapa mapa;

	@Column(name = "origem")
	private String origem;

	@Column(name = "destino")
	private String destino;

	@Column(name = "distancia")
	private Long distancia;

	public Segmento() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segmento other = (Segmento) obj;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (distancia == null) {
			if (other.distancia != null)
				return false;
		} else if (!distancia.equals(other.distancia))
			return false;
		if (mapa == null) {
			if (other.mapa != null)
				return false;
		} else if (!mapa.equals(other.mapa))
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		return true;
	}

	public String getDestino() {
		return destino;
	}

	public Long getDistancia() {
		return distancia;
	}

	public Long getId() {
		return id;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public String getOrigem() {
		return origem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((distancia == null) ? 0 : distancia.hashCode());
		result = prime * result + ((mapa == null) ? 0 : mapa.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		return result;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void setDistancia(Long distancia) {
		this.distancia = distancia;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	@Override
	public String toString() {
		return "Segmento [id=" + id + ", origem=" + origem + ", destino=" + destino + ", distancia=" + distancia + "]";
	}

}
