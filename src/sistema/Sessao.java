package sistema;

import java.util.UUID;

public class Sessao {
	String login;
	String senha;
	UUID id;

	public Sessao(String login, String senha) {
		boolean iniciada = false;
		this.login = login;
		this.senha = senha;
		this.id = gerarId();
	}

	private UUID gerarId() {
		return UUID.randomUUID();
	}

	public String getLogin() {
		return login;
	}

	public UUID getId() {
		return id;
	}

}
