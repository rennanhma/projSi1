package sistema;

import java.util.*;

public class SistemaDeCarona {

	/**
	 * @param args
	 */
	public static List<Usuario> ListaDeUsuarios = new ArrayList<Usuario>();
	public static List<Carona> listaDeCaronas = new ArrayList<Carona>();
	public static List<Sessao> listaDeSessoesAbertas = new ArrayList<Sessao>();
	public static Map<String, List<Carona>> mapaDeCaronas = new HashMap<String, List<Carona>>();
	

	public SistemaDeCarona() {
	}

	// criarUsuario login="mark" senha="m@rk" nome="Mark Zuckerberg"
	// endereco="Palo Alto, California" email="mark@facebook.com"

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		 
		 
		 excecaoCriarUsuario(login, senha, nome, endereco, email);
		 
		 Usuario novoUsuario = new Usuario(login, senha, nome, endereco, email);
	     ListaDeUsuarios.add(novoUsuario);
		

	}

	public void encerrarSistema() {
		System.out.println("Sistema Encerrado");
	}

	// # o m�todo 'abrirSessao' retorna o ID da sess�o
	public String abrirSessao(String login, String senha) throws Exception {
	    
		boolean sessaoAberta = false;
		String id = null;
		if (login == null || login.equals("")) {
			throw new Exception("Login inv�lido");
		}
		if (senha == null || senha.equals("")) {
			throw new Exception("Senha inv�lida");
		}
		
		if (buscaUsuario(login) != null) {
			 if (buscaUsuario(login).getSenha().equals(senha)) {
				for (Sessao sessao : listaDeSessoesAbertas) {
					 if (sessao.getLogin().equals(login)) {
						 sessaoAberta = true;
						 id = sessao.getId();
						 break;
					}
					 
				}
				if (!sessaoAberta) {
					Sessao sessao = new Sessao(login, senha);
					listaDeSessoesAbertas.add(sessao);
					id = sessao.getId();
				}

			}else{

				throw new Exception("Login inv�lido");
			}
			 
		}else{
			
			throw new Exception("Usu�rio inexistente");
		}
		
		return id;
		
	}
	
	


	
	//getAtributoUsuario
	public String getAtributoUsuario(String login, String atributo) throws Exception {
		
		excecaoDeAtributosInvalidos(login, atributo); // lan�a qualquer excecao se o login ou o atributo estiver incorreto
		
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
        
		if (idDaCarona == null || idDaCarona.equals("")) {
			
        	throw new Exception("Identificador do carona � inv�lido");
		}
		if(buscaCaronaID(idDaCarona) == null)
		{
			throw new Exception("Item inexistente");
		}
		else if(atributo == null || atributo.equals(""))
		{
			throw new Exception("Atributo inv�lido");
		}
		else if(!atributo.equals("origem") && !atributo.equals("destino") && !atributo.equals("data") && !atributo.equals("vagas"))
		{
			throw new Exception("Atributo inexistente");
		}
	}
	
	
	public void excecaoDeCriacaoDeCarona(String idDaSessao,String origem,String destino,String data,String hora,String vagas) throws Exception{
		

		if (idDaSessao == null || idDaSessao.equals("")) {
			throw new Exception("Sess�o inv�lida");
		}
		
		if (!isSessaoAberta(idDaSessao)) {
			throw new Exception("Sess�o inexistente");
		}
		if (origem == null || origem.equals("")) {
			throw new Exception("Origem inv�lida");
		}
		
		if (destino == null || destino.equals("")) {
			throw new Exception("Destino inv�lido");
		}
		
		if (data == null || data.equals("") || !Carona.isDataValida(data)) {
			throw new Exception("Data inv�lida");
		}
		
		if (hora == null || hora.equals("") || !Carona.horaValida(hora)) {
			throw new Exception("Hora inv�lida");
		}
		
		if (vagas == null || vagas.equals("")) {
			throw new Exception("Vaga inv�lida");
		}else{
			
			try {
				Integer.parseInt(vagas);
			} catch (Exception e) {
				throw new Exception("Vaga inv�lida");
			}
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

	
	
	
	public static void excecaoCriarUsuario(String login,String senha,String nome,String endereco,String email) throws Exception{
		if (login == null || login.equals("")) {
			throw new Exception("Login inv�lido");
		}
		if (nome == null || nome.equals("")) {
			throw new Exception("Nome inv�lido");
		}
		
		if (email == null || email.equals("")) {
			throw new Exception("Email inv�lido");
		}
        
		for (int i = 0; i < ListaDeUsuarios.size(); i++) {
			if (ListaDeUsuarios.get(i).getLogin().equals(login)) {
				throw new Exception("J� existe um usu�rio com este login");
			}
			if (ListaDeUsuarios.get(i).getEmail().equals(email)) {
				throw new Exception("J� existe um usu�rio com este email");
			}
		}
		
		
	}
	
	public List<Usuario> getUsuarios() {
		return ListaDeUsuarios;
	}

	//Busca Usuario e retorna um usuario a partir do login
	public Usuario buscaUsuario(String login){
		for (int i = 0; i < ListaDeUsuarios.size(); i++) {
			if (ListaDeUsuarios.get(i).getLogin().equals(login)) {
				return ListaDeUsuarios.get(i);
			}
		}
		return null;
	}
	
	public boolean isSessaoAberta(String id){
		
		boolean existeSessaoAberta = false;
		
		for (Sessao sessao : listaDeSessoesAbertas) {
			if (sessao.getId().equals(id)) {
				
				existeSessaoAberta = true;
				break;
			}
		}
		
		return existeSessaoAberta;
		
	}
	
	
	
   public String cadastrarCarona(String idDaSessao, String origem, String destino, String data, String hora, String vagas) throws Exception{
	   excecaoDeCriacaoDeCarona(idDaSessao, origem, destino, data, hora, vagas);
	   int vagasInt = Integer.parseInt(vagas);
	   Carona novaCarona = new Carona(origem, destino, data, hora, vagasInt);
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
   
   



	public String localizarCarona(String idDaSessao, String origem,String destino) throws Exception {
		
        excecaoLocalizarCarona(idDaSessao, origem, destino);
		List<String> caronasEncontradas = new ArrayList<String>();
		String strCaronas = null;
		
		 for (String chave : mapaDeCaronas.keySet()) {
			 
			 for (Carona carona : mapaDeCaronas.get(chave)) {
				 
				
				if (!origem.equals("") && !destino.equals("")) { // lista todas as caronas de uma determinada origem at� um destino
					
					
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
		
			
		 
		 
         strCaronas = caronasEncontradas.toString();
         strCaronas = strCaronas.replace("[", "{");
         strCaronas = strCaronas.replace("]", "}");
         
		return strCaronas.replace(" ", "");
	}
	
	public void excecaoLocalizarCarona(String idDaSessao, String origem, String destino) throws Exception{
		if (idDaSessao == null) {
			throw new Exception("Sess�o inv�lida");
		}
		if (idDaSessao.equals("") || !isSessaoAberta(idDaSessao)) {
			
			throw new Exception("Sess�o inexistente");
		}
		
		if (origem == null) {
			throw new Exception("Origem Inexistente");
		}else{
			
		for (char c : origem.toCharArray()) {
			 if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
				throw new Exception("Origem inv�lida");
			}
		}
	}
		
		if (destino == null) {
			throw new Exception("Destino Inexistente");
		}else{
			for (char c : destino.toCharArray()) {
				 if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
					throw new Exception("Destino inv�lido");
				}
		}
	}
		
		
}
	
	
	public Sessao buscarSessaoId(String idDaSessao) {	
		Sessao sessao = null;
		for (Sessao sessao1 : listaDeSessoesAbertas) {
			if (sessao1.getId().equals(idDaSessao)) {
				sessao = sessao1;
				break;
			}
		}
		return sessao;
	}

	public String getTrajeto(String idDaCarona) throws Exception
	{   
		excecaoGetTrajeto(idDaCarona);
		return buscaCaronaID(idDaCarona).getOrigem() + " - " + buscaCaronaID(idDaCarona).getDestino();
	}
	
	
	public void excecaoGetTrajeto(String idDaCarona) throws Exception{
		
		if (idDaCarona == null) {
			throw new Exception("Trajeto Inv�lido");
		}
		if (idDaCarona.equals("")) {
		  throw new Exception("Trajeto Inexistente");
		}
		
		if (buscaCaronaID(idDaCarona) == null) {
			
			throw new Exception("Trajeto Inexistente");
		}
	}
	
	
	public String getCarona(String idDaCarona) throws Exception
	{   
		excecaoGetCarona(idDaCarona);
		Carona carona = buscaCaronaID(idDaCarona);
		//Jo�o Pessoa para Campina Grande, no dia 25/11/2026, as 06:59
		return carona.getOrigem() + " para " + carona.getDestino() + 
				", no dia " + carona.getData() + ", as " + carona.getHora();
		
		
	}
	

	public void excecaoGetCarona(String idDaCarona) throws Exception{
		 if (idDaCarona == null) {
				throw new Exception("Carona Inv�lida");
				
			}
		if (idDaCarona.equals("") || buscaCaronaID(idDaCarona) == null) {
			
			throw new Exception("Carona Inexistente");
			
		}
	  
		
		

		
		
	}
	
	public static void main(String[] args) throws Exception {
		
		/*SistemaDeCarona sc = new SistemaDeCarona();
		sc.cadastrarCarona("234", "campina", "joao pessoa", "12/03/2012", "14:00", "3");
		sc.cadastrarCarona("2344", "campina", "joao pessoa", "12/03/2012", "15:00", "3");
		sc.cadastrarCarona("234", "campina", "caruaru", "12/03/2012", "15:00", "3");
		sc.cadastrarCarona("234", "campina", "caruaru", "12/03/2012", "19:00", "3");
		
		System.out.println(sc.mapaDeCaronas.toString());*/
		
	
	    
	    
	}
	
	

}
