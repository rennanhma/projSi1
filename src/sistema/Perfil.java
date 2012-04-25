package sistema;

import java.util.ArrayList;
import java.util.List;

public class Perfil {
	
	
	private String login, nome, endereco, email, caronasSeguras, caronasNaoFuncionaram, faltasEmCaronas, presencaEmCaronas;
	private List<String> historicoCaronas, hitoricoVagasEmCaronas;
	
	public Perfil(String nome, String endereco, String email, String login)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
		this.historicoCaronas = new ArrayList<String>();
		this.hitoricoVagasEmCaronas = new ArrayList<String>();
		this.caronasSeguras = "0";
		this.caronasNaoFuncionaram = "0";
		this.faltasEmCaronas = "0";
		this.presencaEmCaronas = "0";
		this.login = login;
	}
	
	
	
	public List<String> getHistoricoCaronas() {
		return historicoCaronas;
	}



	public void adicionaHistoricoCaronas(String idCarona) {
		this.historicoCaronas.add(idCarona);
	}



	public List<String> getHitoricoVagasEmCaronas() {
		return hitoricoVagasEmCaronas;
	}



	public void setHitoricoVagasEmCaronas(List<String> hitoricoVagasEmCaronas) {
		this.hitoricoVagasEmCaronas = hitoricoVagasEmCaronas;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getCaronasSeguras() {
		return caronasSeguras;
	}

	public void setCaronasSeguras(String caronasSeguras) {
		this.caronasSeguras = caronasSeguras;
	}

	public String getCaronasNaoFuncionaram() {
		return caronasNaoFuncionaram;
	}

	public void setCaronasNaoFuncionaram(String caronasNaoFuncionaram) {
		this.caronasNaoFuncionaram = caronasNaoFuncionaram;
	}

	public String getFaltasEmCaronas() {
		return faltasEmCaronas;
	}

	public void setFaltasEmCaronas(String faltasEmCaronas) {
		this.faltasEmCaronas = faltasEmCaronas;
	}

	public String getPresencaEmCaronas() {
		return presencaEmCaronas;
	}

	public void setPresencaEmCaronas(String presencaEmCaronas) {
		this.presencaEmCaronas = presencaEmCaronas;
	}

	
	
	
}
