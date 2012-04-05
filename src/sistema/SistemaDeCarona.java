package sistema;

import java.util.*;

public class SistemaDeCarona {

	/**
	 * @param args
	 */
	private Collection<Usuario> usuarios;

	public SistemaDeCarona() {
		this.usuarios = new ArrayList<Usuario>();
	}

	// criarUsuario login="mark" senha="m@rk" nome="Mark Zuckerberg"
	// endereco="Palo Alto, California" email="mark@facebook.com"

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		Usuario novoUsuario = new Usuario(login, senha, nome, endereco, email);
		if ((login == null) || (login.equals(""))) {
			throw new Exception("Login inválido");
		} else {
			usuarios.add(novoUsuario);
		}

	}

	public void encerrarSistema() {
		System.out.println("Sistema Encerrado");
	}

	// # o método 'abrirSessao' retorna o ID da sessão
	public UUID abrirSessao(String login, String senha) throws Exception {
		Iterator itr = usuarios.iterator();
		while (itr.hasNext()) {
			Usuario element = (Usuario) itr.next();
			if (element.getLogin().equals(login) && element.getSenha().equals(senha)) {
				Sessao sessao = new Sessao(login, senha);
				return sessao.getId();
			}
		}
		throw new Exception("Usuário inexistente");
	}
	
	//getAtributoUsuario
	public String getAtributoUsuario(String login, String atributo) throws Exception{
		if(buscaUsuario(login)!= null && atributo=="nome"){
			return buscaUsuario(login).getNome();
		}
		if(buscaUsuario(login)!= null && atributo=="endereco"){
			return buscaUsuario(login).getEndereco();
		}
		throw new Exception("Atributo inexistente");
		
	}
	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	//Busca Usuario
	public Usuario buscaUsuario(String login){
		Iterator itr = usuarios.iterator();
		while (itr.hasNext()) {
			Usuario element = (Usuario) itr.next();
			if (element.getLogin() == login) {
				return element;
			}
		}
		return null;
	}

}
