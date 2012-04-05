package easy;

import sistema.*;

public class Easy {
	SistemaDeCarona sistema = new SistemaDeCarona();

	public void zerarSistema() {
		sistema = new SistemaDeCarona();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		if (nome == "" || nome == null) {
			throw new Exception("nome inválido");
		} else {

			sistema.criarUsuario(login, senha, nome, endereco, email);
		}
	}

	public void encerrarSistema() {

		sistema.encerrarSistema();
	}

}
