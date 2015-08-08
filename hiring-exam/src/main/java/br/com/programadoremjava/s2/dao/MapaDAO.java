package br.com.programadoremjava.s2.dao;

import br.com.programadoremjava.s2.entity.Mapa;

public class MapaDAO {

	static final Mapa MAPA = new Mapa();
	
	public Mapa put(Mapa mapa) {
		return MAPA;
	}

	public Mapa getById(Long id){
		return MAPA;
	}
	
	public Mapa getByNome(String nome) {
		return MAPA;
	}

}
