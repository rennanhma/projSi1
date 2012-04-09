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
		 
		 Usuario novoUsuario = new Usuario(login, senha, nome, endereco, email);
	     usuarios.add(novoUsuario);
		

	}

	public void encerrarSistema() {
		System.out.println("Sistema Encerrado");
	}

	// # o método 'abrirSessao' retorna o ID da sessão
	public String abrirSessao(String login, String senha) throws Exception {
		if (login == null || login.equals("")) {
			throw new Exception("Login inválido");
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
				throw new Exception("Login inválido");
			}
		}
		throw new Exception("Usuário inexistente");
	}


	
	//getAtributoUsuario
	public String getAtributoUsuario(String login, String atributo) throws Exception {
		
		excecaoDeAtributosInvalidos(login, atributo); // lança qualquer excecao se o login ou o atributo estiver incorreto
		
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
			throw new Exception("Login inválido");
		}
		if (atributo == null || atributo.equals("")) {
			throw new Exception("Atributo inválido");
		}
		
		if (!atributo.equals("nome") && !atributo.equals("endereco") && !atributo.equals("login") && !atributo.equals("senha") && !atributo.equals("email")) {
			throw new Exception("Atributo inexistente");
		}
		
		if (buscaUsuario(login) == null) {
			throw new Exception("Usuário inexistente");
		}
	}
	
	
	
	public String getAtributoCarona(String idDaCarona, String atributo) throws Exception
	{
		  String saida = null;
	       excecaoDeAtributosCaronaInvalidos(idDaCarona, atributo);
		   
	       Carona carona = buscaCaronaID(idDaCarona);
		  
			if(atributo.equals("origem")){
				saida = carona.getOrigem();
			}
			if(atributo.equals("destino")){
				saida = carona.getDestino();			
			}
			if(atributo.equals("data")){
				saida = carona.getData();
			}
			
			if (atributo.equals("vagas")) {
				saida = Integer.toString(carona.getVagas());
			}
		
		//aceita o primeiro que achar e depois sai.
		return saida;
	}
	
	public void excecaoDeAtributosCaronaInvalidos(String idDaCarona,String atributo) throws Exception{

		if(buscaCaronaID(idDaCarona).equals(null))
		{
			throw new Exception("Item inexistente");
		}
		else if(atributo.equals(null) || atributo.equals(""))
		{
			throw new Exception("Atributo inválido");
		}
		else if(!atributo.equals("origem") && !atributo.equals("destino") && !atributo.equals("data") && !atributo.equals("vagas"))
		{
			throw new Exception("Atributo inexistente");
		}
	}
	
	public void excecaoDeCriacaoDeCarona(String idDaSessao,String origem,String destino,String data,String hora,int vagas) throws Exception{
		
		if (idDaSessao == null || idDaSessao.equals(" ")) {
			throw new Exception("Sessão inválida");
		}
		if (origem == null || origem.equals("")) {
			throw new Exception("Origem inválida");
		}
		
		if (destino == null || destino.equals("")) {
			throw new Exception("Destino inválido");
		}
		
		if (data == null || data.equals("")) {
			throw new Exception("Data inválida");
		}
		
		if (hora == null || hora.equals("")) {
			throw new Exception("Hora inválida");
		}
		
		if (vagas <= 0) {
			throw new Exception("Vaga inválida");
		}
	}
	
	
	
	public Carona buscaCaronaID(String idDaCarona)
	{
		
		Iterator<Carona> itListaDeCaronas = listaDeCaronas.iterator();
		Carona saida = null;
		
		while (itListaDeCaronas.hasNext()) {
			  Carona carona = (Carona) itListaDeCaronas.next();
			  if (carona.getIdDaCarona().equals(idDaCarona)) {
				  saida = carona;
				  break;
				 
			}
	
			
		 }
		 
		return saida;
		
	}

	
	
	
	public static void lancaExcecaoDeDadosInvalidos(String login,String senha,String nome,String endereco,String email) throws Exception{
		if (login == null || login.equals("")) {
			throw new Exception("Login inválido");
		}
		if (nome == null || nome.equals("")) {
			throw new Exception("Nome inválido");
		}
		
		if (email == null || email.equals("")) {
			throw new Exception("Email inválido");
		}
        
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getLogin().equals(login)) {
				throw new Exception("Já existe um usuário com este login");
			}
			if (usuarios.get(i).getEmail().equals(email)) {
				throw new Exception("Já existe um usuário com este email");
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
	
	
	
   public String cadastrarCarona(String idDaSessao, String origem, String destino, String data, String hora, int vagas) throws Exception{
	   
	   excecaoDeCriacaoDeCarona(idDaSessao, origem, destino, data, hora, vagas);
	   Carona novaCarona = new Carona(origem, destino, data, hora, vagas);
	   listaDeCaronas.add(novaCarona);
	   
	   if (mapaDeCaronas.containsKey(idDaSessao)) {
		   mapaDeCaronas.get(idDaSessao).add(novaCarona);
	   }
	   else{
		   mapaDeCaronas.put(idDaSessao, new ArrayList<Carona>());
		   mapaDeCaronas.get(idDaSessao).add(novaCarona);
	   }

	   
	   return novaCarona.getIdDaCarona();
		
	}
   
   



	public String localizarCarona(String idDaSessao, String origem,String destino) {
		
		List<String> caronasEncontradas = new ArrayList<String>();
		String strCaronas = null;
		
		 for (String chave : mapaDeCaronas.keySet()) {
			 
			 for (Carona carona : mapaDeCaronas.get(chave)) {
				 
				
				if (!origem.equals("") && !destino.equals("")) { // lista todas as caronas de uma determinada origem até um destino
					
					
					if (carona.getDestino().equals(destino) && carona.getOrigem().equals(origem)) {
						
						caronasEncontradas.add(carona.getIdDaCarona());
			
					  }
					}
					
				   if (!origem.equals("") && destino.equals("")) { //lista todas as caronas daquela origem
						if (carona.getOrigem().equals(origem)) {
							
							caronasEncontradas.add(carona.getIdDaCarona());
							
							}
					}
				   
				   if (origem.equals("") && !destino.equals("")) { // lista todas as caronas para aquele destino
					  if (carona.getDestino().equals(destino)) {
						  
						  caronasEncontradas.add(carona.getIdDaCarona());
						
					}
				}
				   
				   if (origem.equals("") && destino.equals("")) { // lista todas as caronas
					   
					   caronasEncontradas.add(carona.getIdDaCarona());
					 
				   }
					
				}
					
			
			 }
		
			
		 
		 
		// strCaronas = strCaronas+"}";
         strCaronas = caronasEncontradas.toString();
         strCaronas = strCaronas.replace("[", "{");
         strCaronas = strCaronas.replace("]", "}");
         
		 return strCaronas;
	}
	
	
	public String getTrajeto(String idDaCarona)
	{
		return buscaCaronaID(idDaCarona).getOrigem() + " - " + buscaCaronaID(idDaCarona).getDestino();
	}
	
	
	public String getCarona(String idDaCarona)
	{
		Carona carona = buscaCaronaID(idDaCarona);
		//João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59
		return carona.getOrigem() + " para " + carona.getDestino() + 
				", no dia " + carona.getData() + ", as " + carona.getHora();
		
		
	}
	
	public static void main(String[] args) throws Exception {
		
		SistemaDeCarona sc = new SistemaDeCarona();
		sc.cadastrarCarona("234", "campina", "joao pessoa", "12/03/2012", "14:00", 3);
		sc.cadastrarCarona("2344", "campina", "joao pessoa", "12/03/2012", "15:00", 3);
		sc.cadastrarCarona("234", "campina", "caruaru", "12/03/2012", "15:00", 3);
		sc.cadastrarCarona("234", "campina", "caruaru", "12/03/2012", "19:00", 3);
		
		System.out.println(sc.mapaDeCaronas.toString());
		
	
	    
	    
	}
	
	

}
