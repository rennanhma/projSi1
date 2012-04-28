package sistema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Sugestao {

	
	private String pontos , idSugestao, idSessao;
	public List<Resposta> listaDeResposta = new ArrayList<Resposta>();
	
	public Sugestao(String pontos, String idSessao){
		this.pontos = pontos;
		this.idSessao = idSessao;
		this.idSugestao = UUID.randomUUID().toString();
		
		
	}


	public List<Resposta> getlistaDeResposta() {
		return listaDeResposta;
	}


	public void setlistaDeResposta(List<Resposta> listaDeResposta) {
		this.listaDeResposta = listaDeResposta;
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


	public void addResposta(Resposta resposta) {
		listaDeResposta.add(resposta);
	}
	
	
	
}
