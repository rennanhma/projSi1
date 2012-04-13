package sistema;

import java.util.UUID;

public class Solicitacao {
	 private String idDoSolicitador,ponto, id;
	
	public Solicitacao(String idDoSolicitador, String ponto){
		this.idDoSolicitador = idDoSolicitador;
		this.ponto = ponto;
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public String getIdDoSolicitador() {
		return idDoSolicitador;
	}

	public void setIdDoSolicitador(String idDoSolicitador) {
		this.idDoSolicitador = idDoSolicitador;
	}

	public String getPonto() {
		return ponto;
	}

	public void setPonto(String ponto) {
		this.ponto = ponto;
	}
}
