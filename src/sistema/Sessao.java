package sistema;

import java.util.UUID;

public class Sessao {
	
	private String login;
	private String senha;
	private String id;

	public Sessao(String login, String senha) {
		boolean iniciada = false;
		this.login = login;
		this.senha = senha;
		id = UUID.randomUUID().toString();

	}

/*	private String gerarId() {
		return UUID.randomUUID().toString();
	}*/

	public String getLogin() {
		return login;
	}

	public String getId() {
		return id;
	}
	
	

}
