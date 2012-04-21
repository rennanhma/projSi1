package sistema;


//throw new Exception("");

public class Usuario {

	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;
	private String id = null;

	public Usuario(String login, String senha, String nome, String endereco,
			String email) throws Exception {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;


	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws Exception {
		if ((login == null) || (login.equals(""))) {
			throw new Exception("Login inválido");
		} else {
			this.login = login;
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

}