package sistema;

import java.util.UUID;

public class Sugestao {
	private String idSessao,idCarona,ponto, idSugestao;
	
	public Sugestao(String idSessao, String idCarona, String ponto){
		this.idSessao = idSessao;
		this.idCarona = idCarona;
		this.ponto = ponto;
		this.idSugestao = UUID.randomUUID().toString();
	}

	//Quem Sugerio o local
	public String getIdSessao() {
		return idSessao;
	}

	public String getIdSugestao() {
		return idSugestao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	public String getIdCarona() {
		return idCarona;
	}

	//A qual Carona ele Sugeriu
	public void setIdCarona(String idCarona) {
		this.idCarona = idCarona;
	}

	public String getPonto() {
		return ponto;
	}

	//Que sugestao ele propos
	public void setPonto(String ponto) {
		this.ponto = ponto;
	}
	
}
