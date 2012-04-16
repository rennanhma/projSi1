package sistema;

import java.util.UUID;

public class Sugestao {

	
	private String pontos , idSugestao, idSessao;
	
	
	public Sugestao(String pontos, String idSessao){
		this.pontos = pontos;
		this.idSessao = idSessao;
		this.idSugestao = UUID.randomUUID().toString();
		
		
	}


	public String getPontos() {
		return pontos;
	}


	public void setPontos(String pontos) {
		this.pontos = pontos;
	}


	public String getIdSugestao() {
		return idSugestao;
	}


	public void setIdSugestao(String idSugestao) {
		this.idSugestao = idSugestao;
	}


	public String getIdSessao() {
		return idSessao;
	}


	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}
	
	
	
}
