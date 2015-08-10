package br.com.programadoremjava.s2.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.programadoremjava.s2.dao.MapaDAO;
import br.com.programadoremjava.s2.entity.Mapa;
import br.com.programadoremjava.s2.entity.Segmento;

@Path("/mapas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MapaWebservice {

	@GET
	@Path("/{nome}")
	public Mapa findByNome(@PathParam("nome") String nome) {
		return new MapaDAO().findByNome(nome);
	}

	@POST
	@Path("/")
	public Mapa save(Mapa mapa) {
		System.out.println(mapa);
		for (Segmento segmento : mapa.getSegmentos()) {
			segmento.setMapa(mapa);
		}
		new MapaDAO().put(mapa);
		return mapa;
	}

}
