package br.com.programadoremjava.s2.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.programadoremjava.s2.entity.Mapa;
import br.com.programadoremjava.s2.entity.Segmento;

@SessionScoped
@ManagedBean(name = "cadastrarMapas")
public class CadastrarMapas implements Serializable {

	private static final long serialVersionUID = 468858242316764269L;

	private Mapa mapa;

	public CadastrarMapas() {
		this.mapa = new Mapa();
		this.mapa.setNome("Novo Mapa");
		this.setSegmentos(new ArrayList<>());
		this.mapa.setSegmentos(getSegmentos());

		Segmento s1 = new Segmento();
		s1.setOrigem("Origem 1");
		s1.setDestino("Destino 1");
		s1.setDistancia(1l);
		s1.setMapa(mapa);
		getSegmentos().add(s1);

		Segmento s2 = new Segmento();
		s2.setOrigem("Origem 2");
		s2.setDestino("Destino 2");
		s2.setDistancia(2l);
		s2.setMapa(mapa);
		getSegmentos().add(s2);
	}

	public Mapa getMapa() {
		return mapa;
	}

	public String getNome() {
		return getMapa().getNome();
	}

	public List<Segmento> getSegmentos() {
		return getMapa().getSegmentos();
	}

	public void salvar() {
		System.out.println("Enviar mapa pro webservice");
		System.out.println(mapa);
	}

	public void setNome(String nome) {
		getMapa().setNome(nome);
	}

	public void setSegmentos(List<Segmento> segmentos) {
		getMapa().setSegmentos(segmentos);
	}

	public void removerSegmento(Segmento segmento) {
		getSegmentos().remove(segmento);
	}

	public void novoSegmento() {
		Segmento segmento = new Segmento();
		getSegmentos().add(segmento);

		int size = getSegmentos().size();
		segmento.setOrigem(String.format("Origem %d", size));
		segmento.setDestino(String.format("Destino %d", size));
		segmento.setDistancia(Long.valueOf(size));
		segmento.setMapa(mapa);
	}
}
