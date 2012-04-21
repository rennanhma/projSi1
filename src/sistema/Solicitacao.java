package sistema;

import java.util.UUID;

public class Solicitacao {

	private String idSessao, idCarona, ponto, idSolicitacao;

	public Solicitacao(String idSessao, String idCarona, String ponto) {
		this.idSessao = idSessao;
		this.idCarona = idCarona;
		this.ponto = ponto;
		this.idSolicitacao = UUID.randomUUID().toString();
	}

	public String getIdSessao() {
		return idSessao;
	}
	

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}


	public String getIdCarona() {
		return idCarona;
	}

	public void setIdCarona(String idCarona) {
		this.idCarona = idCarona;
	}

	public String getPonto() {
		return ponto;
	}

	public void setPonto(String ponto) {
		this.ponto = ponto;
	}

	public String getIdSolicitacao() {
		return idSolicitacao;
	}

	public void setIdSolicitacao(String idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}

}
