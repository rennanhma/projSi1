package sistema;

import java.util.*;

public class SistemaDeCarona {

	/**
	 * @param args
	 */
	public static List<Usuario> usuarios = new ArrayList<Usuario>();
	public static List<Carona> listaDeCaronas = new ArrayList<Carona>();
	public static List<Carona> listaDeCaronasEncontradas = new ArrayList<Carona>();
	public static Map<String, List<Carona>> mapaDeCaronas = new HashMap<String, List<Carona>>();
	Map<String, List<Carona>> mapaDeCaronasEncontradas = new HashMap<String, List<Carona>>();
	

	public SistemaDeCarona() {
	}

	// criarUsuario login="mark" senha="m@rk" nome="Mark Zuckerberg"
	// endereco="Palo Alto, California" email="mark@facebook.com"

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		 
		 
		lancaExcecaoDeDadosInvalidos(login, senha, nome, endereco, email);
		
		/* excecaoLoginInvalido(login);
		 excecaoUsuarioJaExiste(login);
		 excecaoSenhaInvalido(senha);
		 excecaoNomeInvalido(nome);
		 excecaoEnderecoInvalido(endereco);
		 excecaoEmailInvalido(email);
		 excecaoEmailJaExiste(email);*/
		 
		 Usuario novoUsuario = new Usuario(login, senha, nome, endereco, email);
	     usuarios.add(novoUsuario);
		

	}

	public void encerrarSistema() {
		System.out.println("Sistema Encerrado");
	}

	// # o m�todo 'abrirSessao' retorna o ID da sess�o
	public String abrirSessao(String login, String senha) throws Exception {
		if (login == null || login.equals("")) {
			throw new Exception("Login inv�lido");
		}
		Iterator<Usuario> itr = usuarios.iterator();
		while (itr.hasNext()) {
			Usuario element = (Usuario) itr.next();
			if (element.getLogin().equals(login)
					&& element.getSenha().equals(senha)) {
				Sessao sessao = new Sessao(login, senha);
				return sessao.getId();
			}
			if(element.getLogin().equals(login) && !element.getSenha().equals(senha)) {
				throw new Exception("Login inv�lido");
			}
		}
		throw new Exception("Usu�rio inexistente");
	}


	
	//getAtributoUsuario
	public String getAtributoUsuario(String login, String atributo) throws Exception {
		
		excecaoDeAtributosInvalidos(login, atributo); // lan�a qualquer excecao se o login ou o atributo estiver incorreto
		
		/*excecaoLoginInvalido(login);
		excecaoAtributoInvalido(atributo);
		excecaoAtributoInexistente(atributo);
		excecaoUsuarioInxistente(login);*/
		
		if(atributo.equals("nome")){
			return buscaUsuario(login).getNome();
		}
		if(atributo.equals("endereco")){
			return buscaUsuario(login).getEndereco();
		}
		
		throw new Exception("error");
		
	}
	
	public void excecaoDeAtributosInvalidos(String login,String atributo) throws Exception{
		
		if (login == null || login.equals("")) {
			throw new Exception("Login inv�lido");
		}
		if (atributo == null || atributo.equals("")) {
			throw new Exception("Atributo inv�lido");
		}
		
		if (!atributo.equals("nome") && !atributo.equals("endereco") && !atributo.equals("login") && !atributo.equals("senha") && !atributo.equals("email")) {
			throw new Exception("Atributo inexistente");
		}
		
		if (buscaUsuario(login) == null) {
			throw new Exception("Usu�rio inexistente");
		}
	}
	
	
//	public String getAtributoCarona(String idDaSessao, String atributo) throws Exception {
//		System.out.println(idDaSessao + "  aqui");
//		excecaoDeAtributosCaronaInvalidos(idDaSessao, atributo);
//		
//		if(atributo.equals("origem")){
//			return buscaCaronaID(idDaSessao).get(0).getOrigem();
//		}
//		if(atributo.equals("destino")){
//			return buscaCaronaID(idDaSessao).get(0).getDestino();
//		}
//		if(atributo.equals("data")){
//			return buscaCaronaID(idDaSessao).get(0).getData();
//		}
//		
//		//vagas retona int, metodo retorna String
////		if(atributo.equals("vagas")){
////			return buscaCaronaID(idDaSessao).get(0).getVagas();
////		}
//		
//		throw new Exception("error");
//		
//	}
	
	public String getAtributoCarona(String idDaSessao, String atributo) throws Exception
	{
		String saida = null;
		excecaoDeAtributosCaronaInvalidos(idDaSessao, atributo);

		List<Carona> listaCarona = buscaCaronaID(idDaSessao);
		Iterator<Carona> itCarona = listaCarona.iterator();
		while (itCarona.hasNext())
		{
			Carona carona = itCarona.next();
			if(atributo.equals("origem")){
				saida = carona.getOrigem();
			}
			if(atributo.equals("destino")){
				saida = carona.getDestino();
				break;				
			}
			if(atributo.equals("data")){
				saida = carona.getData();
				break;
			}
		}
		//aceita o primeiro que achar e depois sai.
		return saida;
	}
	
	public void excecaoDeAtributosCaronaInvalidos(String idDaSessao,String atributo) throws Exception{
//		if(idDaSessao.equals(null) || idDaSessao.equals(""))
//		{
//			throw new Exception("Identificador do carona � inv�lido");
//		}
		if(buscaCaronaID(idDaSessao).equals(null))
		{
			throw new Exception("Item inexistente");
		}
		else if(atributo.equals(null) || atributo.equals(""))
		{
			throw new Exception("Atributo inv�lido");
		}
		else if(!atributo.equals("origem") && !atributo.equals("destino") && !atributo.equals("data") && !atributo.equals("vagas"))
		{
			throw new Exception("Atributo inexistente");
		}
	}
	
	
	
	public List<Carona> buscaCaronaID(String idDaSessao)
	{
		List<Carona> usuarioEncontrado = null;
		for (String id : mapaDeCaronas.keySet())
		{
			if(idDaSessao.equals(id))
			{
				usuarioEncontrado = mapaDeCaronas.get(id);
				break;
			}
		}
		return usuarioEncontrado;
	}

	
	
	/*public void excecaoUsuarioInxistente(String login) throws Exception{
		if (buscaUsuario(login) == null) {
			throw new Exception("Usu�rio inexistente");
		}
	}
	
	public void excecaoAtributoInexistente(String atributo) throws Exception{
		if (!atributo.equals("nome") && !atributo.equals("endereco") && !atributo.equals("login") && !atributo.equals("senha") && !atributo.equals("email")) {
			throw new Exception("Atributo inexistente");
		}
		
	}
	public void excecaoAtributoInvalido(String atributo) throws Exception{
		if (atributo == null || atributo.equals("")) {
			throw new Exception("Atributo inv�lido");
		
		}
		
	}
	
	public void excecaoLoginInvalido(String login) throws Exception{
		if (login == null || login.equals("")) {
			throw new Exception("Login inv�lido");
		}
		
	}
	
	public void excecaoSenhaInvalido(String senha) throws Exception{
		if (senha == null || senha.equals("")) {
			throw new Exception("Senha inv�lido");
		}
		
	}
	
	public void excecaoNomeInvalido(String nome) throws Exception{
		if (nome == null || nome.equals("")) {
			throw new Exception("Nome inv�lido");
		}
		
	}
	
	public void excecaoEnderecoInvalido(String endereco) throws Exception{
		if (endereco == null || endereco.equals("")) {
			throw new Exception("Endereco inv�lido");
		}
		
	}
	
	public void excecaoEmailInvalido(String email) throws Exception{
		if (email == null || email.equals("")) {
			throw new Exception("Email inv�lido");
		}
		
	}
	
	public void excecaoUsuarioJaExiste(String login) throws Exception{
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getLogin().equals(login)) {
				throw new Exception("J� existe um usu�rio com este login");
			}
		}
		
	}
	
	public void excecaoEmailJaExiste(String email) throws Exception{
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getEmail().equals(email)) {
				throw new Exception("J� existe um usu�rio com este email");
			}
		}
		
	}
	*/
	
	
	public static void lancaExcecaoDeDadosInvalidos(String login,String senha,String nome,String endereco,String email) throws Exception{
		if (login == null || login.equals("")) {
			throw new Exception("Login inv�lido");
		}
		if (nome == null || nome.equals("")) {
			throw new Exception("Nome inv�lido");
		}
		
		if (email == null || email.equals("")) {
			throw new Exception("Email inv�lido");
		}
        
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getLogin().equals(login)) {
				throw new Exception("J� existe um usu�rio com este login");
			}
			if (usuarios.get(i).getEmail().equals(email)) {
				throw new Exception("J� existe um usu�rio com este email");
			}
		}
		
		
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	//Busca Usuario e retorna um usuario a partir do login
	public Usuario buscaUsuario(String login){
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getLogin().equals(login)) {
				return usuarios.get(i);
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		Carona c = new Carona("o", "oi", "oiji", "oij", 2);
		System.out.println(c.getIdDaCarona().equals(null));
		SistemaDeCarona s = new SistemaDeCarona();
		Usuario usuario = new Usuario("dnlgomes", "danilo", "da", "eu", "lakdjf");
	    usuarios.add(usuario);
	    System.out.println(s.cadastrarCarona("88", "campina", "joao", "12/03", "12:00", 3));
	    Map<String, List<Carona>> l = s.localizarCarona("88", "campina", "joao");
	    List<Carona> a = l.get("88");
	    System.out.println(a.get(0).getOrigem());
	    System.out.println(s.getAtributoCarona("88", "destino"));
		System.out.println(l.get("88").get(0).getIdDaCarona());
	    
	    
	}
	
   public String cadastrarCarona(String idDaSessao, String origem, String destino, String data, String hora, int vagas){
	   
	   Carona novaCarona = new Carona(origem, destino, data, hora, vagas);
	   listaDeCaronas.add(novaCarona);
	   mapaDeCaronas.put(idDaSessao, listaDeCaronas);
	   
	   return idDaSessao;
		
	}


	public Map<String, List<Carona>> localizarCarona(String idDaSessao, String origem,
			String destino) {
		List<Carona> listaDeCaronasAux = new ArrayList<Carona>();
		Carona carona = null;
		List<Carona> listaDeCaronasEncontradasAux = new ArrayList<Carona>();
		 for (String chave : mapaDeCaronas.keySet()) {
			 
			 listaDeCaronasEncontradasAux.clear();
			 
			 listaDeCaronasAux = mapaDeCaronas.get(chave);
			 
			 for (int i = 0; i < listaDeCaronasAux.size(); i++) {
				carona = listaDeCaronasAux.get(i);
				if (carona.getDestino().equals(destino) && carona.getOrigem().equals(origem)) {
					listaDeCaronasEncontradasAux.add(carona);
				}
			}
			 mapaDeCaronasEncontradas.put(idDaSessao, listaDeCaronasEncontradasAux);
		}
			
		
		
		 
		return mapaDeCaronasEncontradas;
	}
	
	public String getTrajeto(String id)
	{
		return buscaCaronaID(id).get(0).getOrigem() + " - " + buscaCaronaID(id).get(0).getDestino();
	}
	
	
	public String getCarona(String id)
	{
		Carona lista = buscaCaronaID(id).get(0);
		//Jo�o Pessoa para Campina Grande, no dia 25/11/2026, as 06:59
		return lista.getOrigem() + " para " + lista.getDestino() + 
				", no dia " + lista.getData() + ", as " + lista.getHora();
		
		
	}

}
