package sistema;

import java.util.ArrayList;
import java.util.List;

public class Perfil {
	
	
	private String login, nome, endereco, email;
	int caronasSeguras;
	int caronasNaoFuncionaram;
	int presencaEmCaronas;
	int faltasEmCaronas;
	private List<String> historicoCaronas, historicoVagasEmCaronas;
	
	public Perfil(String nome, String endereco, String email, String login)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
		this.historicoCaronas = new ArrayList<String>();
		this.historicoVagasEmCaronas = new ArrayList<String>();
		this.caronasSeguras = 0;
		this.caronasNaoFuncionaram = 0;
		this.faltasEmCaronas = 0;
		this.presencaEmCaronas = 0;
		this.login = login;
	}
	
	
	
	public List<String> getHistoricoCaronas() {
		return historicoCaronas;
	}



	public void adicionaHistoricoCaronas(String idCarona) {
		this.historicoCaronas.add(idCarona);
	}
	
	public void addhistoricoVagasEmCaronas(String idCarona) {
		this.historicoVagasEmCaronas.add(idCarona);
	}



	public List<String> getHitoricoVagasEmCaronas() {
		return historicoVagasEmCaronas;
	}



	public void setHitoricoVagasEmCaronas(List<String> hitoricoVagasEmCaronas) {
		this.historicoVagasEmCaronas = hitoricoVagasEmCaronas;
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


	public int getCaronasSeguras() {
		return caronasSeguras;
	}

	public void setCaronasSeguras(int caronasSeguras) {
		this.caronasSeguras = caronasSeguras;
	}

	public int getCaronasNaoFuncionaram() {
		return caronasNaoFuncionaram;
	}

	public void setCaronasNaoFuncionaram(int caronasNaoFuncionaram) {
		this.caronasNaoFuncionaram = caronasNaoFuncionaram;
	}

	public int getFaltasEmCaronas() {
		return faltasEmCaronas;
	}

	public void setFaltasEmCaronas(int faltasEmCaronas) {
		this.faltasEmCaronas = faltasEmCaronas;
	}

	public int getPresencaEmCaronas() {
		return presencaEmCaronas;
	}

	public void setPresencaEmCaronas(int presencaEmCaronas) {
		this.presencaEmCaronas = presencaEmCaronas;
	}

	
	
	
}
