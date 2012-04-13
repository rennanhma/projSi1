package sistema;

import java.util.UUID;

public class Solicitacao {
	 private String idDoSolicitador,ponto, id;
	 private Carona carona;
	
	public Solicitacao(String idDoSolicitador, String ponto, Carona carona){
		this.idDoSolicitador = idDoSolicitador;
		this.ponto = ponto;
		this.id = UUID.randomUUID().toString();
		this.carona = carona;
	}

	public Carona getCarona() {
		return carona;
	}

	public void setCarona(Carona carona) {
		this.carona = carona;
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
