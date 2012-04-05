package easy;

import sistema.*;

public class Easy {
	SistemaDeCarona sistema = new SistemaDeCarona();

	public void zerarSistema() {
		sistema = new SistemaDeCarona();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {

		sistema.criarUsuario(login, senha, nome, endereco, email);
	}

	public void encerrarSistema() {

		sistema.encerrarSistema();
	}
	public void abrirSessao(String login,String senha){
		sistema.abrirSessao(login, senha);
	}

}
