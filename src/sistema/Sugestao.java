package sistema;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Sugestao {

	
	private String pontos , idSugestao, idSessao;
	public Map<Sugestao, Resposta> mapaDeResposta = new HashMap<Sugestao, Resposta>();
	
	public Sugestao(String pontos, String idSessao){
		this.pontos = pontos;
		this.idSessao = idSessao;
		this.idSugestao = UUID.randomUUID().toString();
		
		
	}


	public Map<Sugestao, Resposta> getMapaDeResposta() {
		return mapaDeResposta;
	}


	public void setMapaDeResposta(Map<Sugestao, Resposta> mapaDeResposta) {
		this.mapaDeResposta = mapaDeResposta;
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


	public void addResposta(Sugestao sugestao,Resposta resposta) {
		mapaDeResposta.put(sugestao, resposta);
	}
	
	
	
}
