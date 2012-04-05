package sistema;

import java.util.*;

public class SistemaDeCarona {

	/**
	 * @param args
	 */
	private List<Usuario> usuarios;

	public SistemaDeCarona() {
		this.usuarios = new ArrayList<Usuario>();
	}

	// criarUsuario login="mark" senha="m@rk" nome="Mark Zuckerberg"
	// endereco="Palo Alto, California" email="mark@facebook.com"

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
			Usuario novoUsuario = new Usuario(login, senha, nome, endereco, email);
			usuarios.add(novoUsuario);
			
	}

	public void encerrarSistema() {
		System.out.println("Sistema Encerrado");
	}

	public void abrirSessao(String login, String senha) {
		// TODO Criar
		
	}

}
