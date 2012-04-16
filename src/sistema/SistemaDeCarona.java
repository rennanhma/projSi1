package sistema;

import java.util.*;

public class SistemaDeCarona {

	/**
	 * @param args
	 */
	public List<Usuario> ListaDeUsuarios = new ArrayList<Usuario>();
	public List<Carona> listaDeCaronas = new ArrayList<Carona>();
	public List<Sessao> listaDeSessoesAbertas = new ArrayList<Sessao>();
	public Map<String, List<Carona>> mapaDeCaronas = new HashMap<String, List<Carona>>();
	

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

	// # o método 'abrirSessao' retorna o ID da sessão
	public String abrirSessao(String login, String senha) throws Exception {
	    
		boolean sessaoAberta = false;
		String id = null;
		if (login == null || login.equals("")) {
			throw new Exception("Login inválido");
		}
		if (senha == null || senha.equals("")) {
			throw new Exception("Senha inválida");
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

				throw new Exception("Login inválido");
			}
			 
		}else{
			
			throw new Exception("Usuário inexistente");
		}
		
		return id;
		
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
			
			if (atributo.equals("Ponto de Encontro")) {
				saida = carona.getPontoDeEncontro().get(0);
			}
		
		//aceita o primeiro que achar e depois sai.
		return saida;
	}
	
	public void excecaoDeAtributosCaronaInvalidos(String idDaCarona,String atributo) throws Exception{
        
		if (idDaCarona == null || idDaCarona.equals("")) {
			
        	throw new Exception("Identificador do carona é inválido");
		}
		if(buscaCaronaID(idDaCarona) == null)
		{
			throw new Exception("Item inexistente");
		}
		else if(atributo == null || atributo.equals(""))
		{
			throw new Exception("Atributo inválido");
		}
		else if(!atributo.equals("origem") && !atributo.equals("destino") && !atributo.equals("data") && !atributo.equals("vagas") && !atributo.equals("Ponto de Encontro"))
		{
			throw new Exception("Atributo inexistente");
		}
	}
	
	
	public void excecaoDeCriacaoDeCarona(String idDaSessao,String origem,String destino,String data,String hora,String vagas) throws Exception{
		

		if (idDaSessao == null || idDaSessao.equals("")) {
			throw new Exception("Sessão inválida");
		}
		
		if (!isSessaoAberta(idDaSessao)) {
			throw new Exception("Sessão inexistente");
		}
		if (origem == null || origem.equals("")) {
			throw new Exception("Origem inválida");
		}
		
		if (destino == null || destino.equals("")) {
			throw new Exception("Destino inválido");
		}
		
		if (data == null || data.equals("") || !Carona.isDataValida(data)) {
			throw new Exception("Data inválida");
		}
		
		if (hora == null || hora.equals("") || !Carona.horaValida(hora)) {
			throw new Exception("Hora inválida");
		}
		
		if (vagas == null || vagas.equals("")) {
			throw new Exception("Vaga inválida");
		}else{
			
			try {
				Integer.parseInt(vagas);
			} catch (Exception e) {
				throw new Exception("Vaga inválida");
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

	
	
	
	public void excecaoCriarUsuario(String login,String senha,String nome,String endereco,String email) throws Exception{
		if (login == null || login.equals("")) {
			throw new Exception("Login inválido");
		}
		if (nome == null || nome.equals("")) {
			throw new Exception("Nome inválido");
		}
		
		if (email == null || email.equals("")) {
			throw new Exception("Email inválido");
		}
        
		for (int i = 0; i < ListaDeUsuarios.size(); i++) {
			if (ListaDeUsuarios.get(i).getLogin().equals(login)) {
				throw new Exception("Já existe um usuário com este login");
			}
			if (ListaDeUsuarios.get(i).getEmail().equals(email)) {
				throw new Exception("Já existe um usuário com este email");
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
	   Sessao sessao = buscarSessaoId(idDaSessao);
	   int vagasInt = Integer.parseInt(vagas);
	   Carona novaCarona = new Carona(origem, destino, data, hora, vagasInt);
	   novaCarona.setDonoDaCarona(buscaUsuario(sessao.getLogin()));
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
		
			
		 
		 
         strCaronas = caronasEncontradas.toString();
         strCaronas = strCaronas.replace("[", "{");
         strCaronas = strCaronas.replace("]", "}");
         strCaronas = strCaronas.replace(" ", "");
         
		 return strCaronas;
	}
	
	public void excecaoLocalizarCarona(String idDaSessao, String origem, String destino) throws Exception{
		if (idDaSessao == null) {
			throw new Exception("Sessão inválida");
		}
		if (idDaSessao.equals("") || !isSessaoAberta(idDaSessao)) {
			
			throw new Exception("Sessão inexistente");
		}
		
		if (origem == null) {
			throw new Exception("Origem Inexistente");
		}else{
			
		for (char c : origem.toCharArray()) {
			 if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
				throw new Exception("Origem inválida");
			}
		}
	}
		
		if (destino == null) {
			throw new Exception("Destino Inexistente");
		}else{
			for (char c : destino.toCharArray()) {
				 if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
					throw new Exception("Destino inválido");
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
			throw new Exception("Trajeto Inválido");
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
		//João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59
		return carona.getOrigem() + " para " + carona.getDestino() + 
				", no dia " + carona.getData() + ", as " + carona.getHora();
		
		
	}
	

	public void excecaoGetCarona(String idDaCarona) throws Exception{
		 if (idDaCarona == null) {
				throw new Exception("Carona Inválida");
				
			}
		if (idDaCarona.equals("") || buscaCaronaID(idDaCarona) == null) {
			
			throw new Exception("Carona Inexistente");
			
		}
		
	  
		
		

		
		
	}
	
	public void encerrarSessao(String login) {
		for (Sessao sessao1 : listaDeSessoesAbertas) {
			if (sessao1.getLogin().equals(login)) {
				listaDeSessoesAbertas.remove(sessao1);
				break;
			}

		}

	}
	
			
	public String sugerirPontoEncontro(String idSessao,String idCarona,String pontos){
		Carona carona = buscaCaronaID(idCarona);
		Sugestao sugestao = new Sugestao (pontos, idSessao);
		carona.addSugestao(sugestao);
		
		return sugestao.getIdSugestao();
	}
	
   public String responderSugestaoPontoEncontro(String idSessao, String idCarona, String idSugestao, String pontos){
		    
		    Sessao sessao = buscarSessaoId(idSessao);
		    Carona carona = buscaCaronaID(idCarona);
		    if (sessao.getLogin().equals(carona.getDonoDaCarona().getLogin())) {
					for (Sugestao sugestao : carona.getSugestoes()) {
						if (!sugestao.getIdSugestao().equals(pontos)) {
					       carona.getSugestoes().clear();
					       carona.addSugestao(new Sugestao(pontos, idSessao));						
					}
				
				}
		    	//carona.addPontoDeEncontro(pontos);
			}
		    
		return null;
	}

					
	public String solicitarVagaPontoEncontro (String idSessao, String idCarona, String ponto){
				/*
				 * 1- Colocar solicitacao em carona
				 * 2- Return ID
				 */
				
				Carona carona = buscaCaronaID(idCarona);
				Solicitacao solicitacao = new Solicitacao(idSessao, idCarona, ponto);
				
				for(Sugestao sugestao : carona.getSugestoes()){
						if (sugestao.getPontos().contains(ponto)) {
							//solicitacao.setPonto(ponto);
							carona.addSolicitacao(solicitacao);
							carona.addPontoDeEncontro(ponto);
							break;
						}
					}
					/*if(pontoDeEncontro.equals(ponto)){
						carona.addSolicitacao(solicitacao);
						break;
					}*/
				
				return solicitacao.getIdSolicitacao();
			}
	
/*
 * expect "Campina Grande" getAtributoSolicitacao idSolicitacao=${solicitacao1ID} atributo="origem"
expect "João Pessoa" getAtributoSolicitacao idSolicitacao=${solicitacao1ID} atributo="destino"
expect "Mark Zuckerberg" getAtributoSolicitacao idSolicitacao=${solicitacao1ID} atributo="Dono da carona"
expect "William Henry Gates III" getAtributoSolicitacao idSolicitacao=${solicitacao1ID} atributo="Dono da solicitacao"
expect "Acude Velho" getAtributoSolicitacao idSolicitacao=${solicitacao1ID} atributo="Ponto de Encontro"
 */
	public String getAtributoSolicitacao(String idSolicitacao, String atributo) throws Exception{
		
		Solicitacao solicitacao = buscaSolicitacao(idSolicitacao);
		Carona carona = buscaCaronaID(solicitacao.getIdCarona());
		String resposta = null;
		
		if(atributo.equals("origem")){
			resposta = carona.getOrigem();			
		}
		else if(atributo.equals("destino")){
			resposta = carona.getDestino();
		}
		else if(atributo.equals("Dono da carona")){
			resposta = carona.getDonoDaCarona().getNome();
		}
		else if(atributo.equals("Dono da solicitacao")){
			Usuario usuario= buscaUsuario((buscarSessaoId(solicitacao.getIdSessao()).getLogin()));
			resposta = usuario.getNome();
		}
		else if(atributo.equals("Ponto de Encontro")){
			resposta = carona.getPontoDeEncontro().get(0);
		}
		return resposta;
	}
	
	public Solicitacao buscaSolicitacao(String idSolicitacao) throws Exception{
		Solicitacao solicitacao = null;
		for(Carona carona1 : listaDeCaronas){
			for(Solicitacao solicitacao1 : carona1.getListaDeSolicitacao()){
				if(solicitacao1.getIdSolicitacao().equals(idSolicitacao)){
					solicitacao = solicitacao1;
					break;
				}
			
			}
		//	break;
		}
		if (solicitacao == null) {
			throw new Exception("Solicitação inexistente");
		}
		return solicitacao;
	}
	
	public Sugestao buscaSugestao(String idSugestao,String idCarona){
		Sugestao sugestaoEncontrada = null;
		Carona carona = buscaCaronaID(idCarona);
		System.out.println("     "+carona.getSugestoes().get(0).getPontos());
		for (Sugestao sugestao : carona.getSugestoes()) {
			if (sugestao.getIdSugestao().equals(idSugestao)) {
				sugestaoEncontrada = sugestao;
			}
		}
		return sugestaoEncontrada;
	}
	

	
	public void aceitarSolicitacaoPontoEncontro(String idSessao, String idSolicitacao) throws Exception{
		Solicitacao solicitacao = buscaSolicitacao(idSolicitacao);
		Sessao sessao = buscarSessaoId(idSessao);
		Carona carona = buscaCaronaID(solicitacao.getIdCarona());
		if (buscaUsuario(sessao.getLogin()).getLogin().equals(carona.getDonoDaCarona().getLogin())) {
			carona.setVagas(carona.getVagas()-1);
		//	System.out.println(carona.listaDeSolicitacao.size());
			carona.getListaDeSolicitacao().remove(solicitacao);
		//	System.out.println(carona.listaDeSolicitacao.size());
		}
	}
	
//	desistirRequisicao idSessao=${sessaoMark} idCarona=${carona4ID} idSugestao=${sugestaoID}
	
	public void desistirRequisicao(String idSessao, String idCarona, String idSugestao){
		Sessao sessao = buscarSessaoId(idSessao);
		Carona carona = buscaCaronaID(idCarona);
		Sugestao sugestao = buscaSugestao(idSugestao, idCarona);
		if (sessao.getLogin().equals(carona.getDonoDaCarona().getLogin())) {
		   if (sugestao != null) {
			 carona.rmSugestoes(sugestao);
		}
	}
		

	}
	
	public static void main(String[] args) throws Exception {
		SistemaDeCarona sistema = new SistemaDeCarona();
		sistema.criarUsuario("danilo", "daniloSenha", "Danilao", "rua do danilo", "Email@");
		sistema.criarUsuario("ght1", "senhaght", "Ght Marques", "Rua do Ght", "ght@emai");
		
		String idSessaoDanilo = sistema.abrirSessao("danilo", "daniloSenha");
		String idCarona = sistema.cadastrarCarona(idSessaoDanilo, "origem1", "destino1", "12/04/2013", "12:00", "3");
		
		sistema.encerrarSessao("danilo");
		String idGht = sistema.abrirSessao("ght1", "senhaght");
		//ADD SUGESTAO
		String idSugestao = sistema.sugerirPontoEncontro(idGht, idCarona, "acude Vei;praça");
		Carona carona = sistema.buscaCaronaID(idCarona);
		//IMPRIME LISTA DE SUGESTAOS
		System.out.println(carona.getSugestoes().toString());
		sistema.encerrarSessao("ght1");
		
		String idSessaoDanilo2 = sistema.abrirSessao("danilo", "daniloSenha");
		
		sistema.responderSugestaoPontoEncontro(idSessaoDanilo2, idCarona, idSugestao, "acude Vei");
		System.out.println(carona.getSugestoes().toString());
		//DESISTI de Susgestao
		sistema.desistirRequisicao(idSessaoDanilo2, idCarona, idSugestao);
		System.out.println(carona.getSugestoes().toString());
		System.out.println(carona.getSugestoes().toString());
		sistema.encerrarSessao("danilo");
		
		/*String idGht2 = sistema.abrirSessao("ght1", "senhaght");
		String idSolicitacao = sistema.solicitarVagaPontoEncontro(idGht2, idCarona, "Parque da crianca");*/
		
		System.out.println(Carona.listaDeSugestoes.get(0).getPontos().toString());
		
		
		
	    
	}
	
	

}
