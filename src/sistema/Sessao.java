package sistema;

import java.util.UUID;

public class Sessao {
	String login;
	String senha;
	String id;

	public Sessao(String login, String senha) {
		boolean iniciada = false;
		this.login = login;
		this.senha = senha;
		this.id = gerarId();
	}

	private String gerarId() {
		return UUID.randomUUID().toString();
	}

	public String getLogin() {
		return login;
	}

	public String getId() {
		return id;
	}
	
	

}
