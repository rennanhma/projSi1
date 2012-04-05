package sistema;

//throw new Exception("");
public class Usuario {

	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;

	public Usuario(String login, String senha, String nome, String endereco,
			String email) throws Exception {
		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
	}

	public String getLogin() {

		return login;
	}

	private void setLogin(String login) throws Exception {
		if (login == "" || login == null) {
			throw new Exception("Login inválido");
		} else {
			this.login = login;
		}
	}

	public String getSenha() {
		return senha;
	}

	private void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) throws Exception {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	private void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	private void setEmail(String email) throws Exception {
		this.email = email;

	}

}
