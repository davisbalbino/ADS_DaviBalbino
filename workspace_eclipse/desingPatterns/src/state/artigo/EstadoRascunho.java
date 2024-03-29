package state.artigo;

import java.time.LocalDate;

public class EstadoRascunho implements Estado {

	private Artigo artigo;
	
	public EstadoRascunho(Artigo artigo) {
		this.artigo = artigo;
	}
	
	@Override
	public void publicar() {
		GerenteDeSeguranca gerenteSeguranca = GerenteDeSeguranca.getInstace();
		
		if(gerenteSeguranca.ehUsuarioAutor()) {
			this.artigo.transitarEstadoPara(new EstadoRevisando(artigo));
			this.artigo.getLogHistorico().add("Transitado para REVISADO em "+ LocalDate.now());
			return;
		}else {
			throw new RuntimeException("Usuario não tem permissão para publicar");
		}
		
	}
	
	@Override
	public void reprovar() {
		//faz nada
	}
	
}
