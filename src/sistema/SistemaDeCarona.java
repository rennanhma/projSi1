package sistema;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import persistencia.CriarXML;
import persistencia.InterfaceXML;

public class SistemaDeCarona {

	/**
	 * @param args
	 */
	public List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
	public List<Perfil> listaDePerfis = new ArrayList<Perfil>();
	public List<Carona> listaDeCaronas = new ArrayList<Carona>();
	public List<Sessao> listaDeSessoesAbertas = new ArrayList<Sessao>();
	private boolean desistirSolicitacao;

	public SistemaDeCarona() {
	}

	// criarUsuario login="mark" senha="m@rk" nome="Mark Zuckerberg"
	// endereco="Palo Alto, California" email="mark@facebook.com"

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {

		excecaoCriarUsuario(login, senha, nome, endereco, email);

		Usuario novoUsuario = new Usuario(login, senha, nome, endereco, email);
		listaDeUsuarios.add(novoUsuario);
		Perfil perfil = new Perfil(nome, endereco, email, login);// criar perfil
																	// do
																	// usuario
		listaDePerfis.add(perfil);

	}

	public void encerrarSistema() {

		CriarXML cXML = new CriarXML();
		cXML.criaXMLUsuarios(listaDeUsuarios);
		InterfaceXML interXML = new InterfaceXML("Perfis", listaDePerfis);
		interXML.saveData();
		interXML = new InterfaceXML("Usuarios", listaDeUsuarios);
		interXML.saveData();
		interXML = new InterfaceXML("Caronas", listaDeCaronas);
		interXML.saveData();

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
					if (buscaUsuario(login).getId() == null) {
						Sessao sessao = new Sessao(login, senha);
						listaDeSessoesAbertas.add(sessao);
						id = sessao.getId();
						buscaUsuario(login).setId(id);
					} else {
						Sessao sessao = new Sessao(login, senha);
						sessao.setID(buscaUsuario(login).getId());
						listaDeSessoesAbertas.add(sessao);
						id = sessao.getId();
					}

				}

			} else {

				throw new Exception("Login inválido");
			}

		} else {

			throw new Exception("Usuário inexistente");
		}

		return id;

	}

	// getAtributoUsuario
	public String getAtributoUsuario(String login, String atributo)
			throws Exception {

		excecaoDeAtributosInvalidos(login, atributo); // lança qualquer excecao
														// se o login ou o
														// atributo estiver
														// incorreto

		if (atributo.equals("nome")) {
			return buscaUsuario(login).getNome();
		}
		if (atributo.equals("endereco")) {
			return buscaUsuario(login).getEndereco();
		}

		throw new Exception("error");

	}

	public void excecaoDeAtributosInvalidos(String login, String atributo)
			throws Exception {

		if (login == null || login.equals("")) {
			throw new Exception("Login inválido");
		}
		if (atributo == null || atributo.equals("")) {
			throw new Exception("Atributo inválido");
		}

		if (!atributo.equals("nome") && !atributo.equals("endereco")
				&& !atributo.equals("login") && !atributo.equals("senha")
				&& !atributo.equals("email")) {
			throw new Exception("Atributo inexistente");
		}

		if (buscaUsuario(login) == null) {
			throw new Exception("Usuário inexistente");
		}
	}

	public String getAtributoCarona(String idDaCarona, String atributo)
			throws Exception {
		String saida = "";
		excecaoDeAtributosCaronaInvalidos(idDaCarona, atributo);

		Carona carona = buscaCaronaID(idDaCarona);

		if (atributo.equals("origem")) {
			saida = carona.getOrigem();
		}
		if (atributo.equals("destino")) {
			saida = carona.getDestino();
		}
		if (atributo.equals("data")) {
			saida = carona.getData();
		}

		if (atributo.equals("vagas")) {
			saida = Integer.toString(carona.getVagas());
		}

		if (atributo.equals("Ponto de Encontro")) {
			if (!carona.getPontoDeEncontro().isEmpty()) {
				saida = carona.getPontoDeEncontro().toString();
                 saida = saida.replace("[", "");
                 saida = saida.replace("]", "");
		

			}
			
		}

		// aceita o primeiro que achar e depois sai.
		return saida;
	}

	public void excecaoDeAtributosCaronaInvalidos(String idDaCarona,
			String atributo) throws Exception {

		if (idDaCarona == null || idDaCarona.equals("")) {

			throw new Exception("Identificador do carona é inválido");
		}
		if (buscaCaronaID(idDaCarona) == null) {
			throw new Exception("Item inexistente");
		} else if (atributo == null || atributo.equals("")) {
			throw new Exception("Atributo inválido");
		} else if (!atributo.equals("origem") && !atributo.equals("destino")
				&& !atributo.equals("data") && !atributo.equals("vagas")
				&& !atributo.equals("Ponto de Encontro")) {
			throw new Exception("Atributo inexistente");
		}
	}

	public void excecaoDeCriacaoDeCarona(String idDaSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws Exception {

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

		if (data == null || data.equals("") || !auxiliar.TrataDatas.isDataValida(data)) {
			throw new Exception("Data inválida");
		}

		if (hora == null || hora.equals("") || !auxiliar.TrataDatas.horaValida(hora)) {
			throw new Exception("Hora inválida");
		}

		if (vagas == null || vagas.equals("")) {
			throw new Exception("Vaga inválida");
		} else {

			try {
				Integer.parseInt(vagas);
			} catch (Exception e) {
				throw new Exception("Vaga inválida");
			}
		}

	}

	public Carona buscaCaronaID(String idCarona) {

		Iterator<Carona> itListaDeCaronas = listaDeCaronas.iterator();
		Carona saida = null;

		while (itListaDeCaronas.hasNext()) {
			Carona carona = (Carona) itListaDeCaronas.next();
			if (carona.getIdDaCarona().equals(idCarona)) {
				saida = carona;
				break;

			}

		}

		return saida;

	}

	public void excecaoCriarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		if (login == null || login.equals("")) {
			throw new Exception("Login inválido");
		}
		if (nome == null || nome.equals("")) {
			throw new Exception("Nome inválido");
		}

		if (email == null || email.equals("")) {
			throw new Exception("Email inválido");
		}

		for (int i = 0; i < listaDeUsuarios.size(); i++) {
			if (listaDeUsuarios.get(i).getLogin().equals(login)) {
				throw new Exception("Já existe um usuário com este login");
			}
			if (listaDeUsuarios.get(i).getEmail().equals(email)) {
				throw new Exception("Já existe um usuário com este email");
			}
		}

	}

	public List<Usuario> getUsuarios() {
		return listaDeUsuarios;
	}

	// Busca Usuario e retorna um usuario a partir do login
	public Usuario buscaUsuario(String login) {
		for (int i = 0; i < listaDeUsuarios.size(); i++) {
			if (listaDeUsuarios.get(i).getLogin().equals(login)) {
				return listaDeUsuarios.get(i);
			}
		}
		return null;
	}

	public boolean isSessaoAberta(String id) {

		boolean existeSessaoAberta = false;

		for (Sessao sessao : listaDeSessoesAbertas) {
			if (sessao.getId().equals(id)) {

				existeSessaoAberta = true;
				break;
			}
		}

		return existeSessaoAberta;

	}

	public String cadastrarCarona(String idDaSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws Exception {
		excecaoDeCriacaoDeCarona(idDaSessao, origem, destino, data, hora, vagas);
		Sessao sessao = buscarSessaoId(idDaSessao);
		int vagasInt = Integer.parseInt(vagas);
		Carona novaCarona = new Carona(origem, destino, data, hora, vagasInt);
		novaCarona.setDonoDaCarona(buscaUsuario(sessao.getLogin()));
		listaDeCaronas.add(novaCarona);

		String login = sessao.getLogin();
		String idCarona = novaCarona.getIdDaCarona();
		addCaronaNoHistorico(login, idCarona);

		Usuario usuario = buscaUsuario(sessao.getLogin());
		usuario.addCarona(novaCarona);

		return novaCarona.getIdDaCarona();

	}

	/*
	 * public String localizarCarona(String idDaSessao, String origem,String
	 * destino) throws Exception {
	 * 
	 * excecaoLocalizarCarona(idDaSessao, origem, destino); List<String>
	 * caronasEncontradas = new ArrayList<String>(); String strCaronas = null;
	 * 
	 * for (String chave : mapaDeCaronas.keySet()) {
	 * 
	 * for (Carona carona : mapaDeCaronas.get(chave)) {
	 * 
	 * 
	 * if (!origem.equals("") && !destino.equals("")) { // lista todas as
	 * caronas de uma determinada origem até um destino
	 * 
	 * 
	 * if (carona.getDestino().equals(destino) &&
	 * carona.getOrigem().equals(origem)) {
	 * 
	 * caronasEncontradas.add(carona.getIdDaCarona());
	 * 
	 * } }
	 * 
	 * if (!origem.equals("") && destino.equals("")) { //lista todas as caronas
	 * daquela origem if (carona.getOrigem().equals(origem)) {
	 * 
	 * caronasEncontradas.add(carona.getIdDaCarona());
	 * 
	 * } }
	 * 
	 * if (origem.equals("") && !destino.equals("")) { // lista todas as caronas
	 * para aquele destino if (carona.getDestino().equals(destino)) {
	 * 
	 * caronasEncontradas.add(carona.getIdDaCarona());
	 * 
	 * } }
	 * 
	 * if (origem.equals("") && destino.equals("")) { // lista todas as caronas
	 * 
	 * caronasEncontradas.add(carona.getIdDaCarona());
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * strCaronas = caronasEncontradas.toString(); strCaronas =
	 * strCaronas.replace("[", "{"); strCaronas = strCaronas.replace("]", "}");
	 * strCaronas = strCaronas.replace(" ", "");
	 * 
	 * return strCaronas; }
	 */

	public String localizarCarona(String idSessao, String origem, String destino)
			throws Exception {

		excecaoLocalizarCarona(idSessao, origem, destino);
		List<String> caronasEncontradas = new ArrayList<String>();
		String strCaronas = null;

		if (isSessaoAberta(idSessao)) {

			for (Carona carona : listaDeCaronas) {

				if (!origem.equals("") && !destino.equals("")) { // lista todas
																	// as
																	// caronas
																	// de uma
																	// determinada
																	// origem
																	// até um
																	// destino

					if (carona.getDestino().equals(destino)
							&& carona.getOrigem().equals(origem)) {

						caronasEncontradas.add(carona.getIdDaCarona());

					}
				}

				if (!origem.equals("") && destino.equals("")) { // lista todas
																// as caronas
																// daquela
																// origem
					if (carona.getOrigem().equals(origem)) {

						caronasEncontradas.add(carona.getIdDaCarona());

					}
				}

				if (origem.equals("") && !destino.equals("")) { // lista todas
																// as caronas
																// para aquele
																// destino
					if (carona.getDestino().equals(destino)) {

						caronasEncontradas.add(carona.getIdDaCarona());

					}
				}

				if (origem.equals("") && destino.equals("")) { // lista todas as
																// caronas

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

	public void excecaoLocalizarCarona(String idDaSessao, String origem,
			String destino) throws Exception {
		if (idDaSessao == null) {
			throw new Exception("Sessão inválida");
		}
		if (idDaSessao.equals("") || !isSessaoAberta(idDaSessao)) {

			throw new Exception("Sessão inexistente");
		}

		if (origem == null) {
			throw new Exception("Origem Inexistente");
		} else {

			for (char c : origem.toCharArray()) {
				if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
					throw new Exception("Origem inválida");
				}
			}
		}

		if (destino == null) {
			throw new Exception("Destino Inexistente");
		} else {
			for (char c : destino.toCharArray()) {
				if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
					throw new Exception("Destino inválido");
				}
			}
		}

	}

	public Sessao buscarSessaoId(String idSessao) {
		Sessao sessao = null;
		for (Sessao sessao1 : listaDeSessoesAbertas) {
			if (sessao1.getId().equals(idSessao)) {
				sessao = sessao1;
				break;
			}
		}
		return sessao;
	}

	public String getTrajeto(String idDaCarona) throws Exception {
		excecaoGetTrajeto(idDaCarona);
		return buscaCaronaID(idDaCarona).getOrigem() + " - "
				+ buscaCaronaID(idDaCarona).getDestino();
	}

	public void excecaoGetTrajeto(String idDaCarona) throws Exception {

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

	public String getCarona(String idDaCarona) throws Exception {
		excecaoGetCarona(idDaCarona);
		Carona carona = buscaCaronaID(idDaCarona);
		// João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59
		return carona.getOrigem() + " para " + carona.getDestino()
				+ ", no dia " + carona.getData() + ", as " + carona.getHora();

	}

	public void excecaoGetCarona(String idDaCarona) throws Exception {
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

	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws Exception {
		excecaoDesistirSolicitacao(desistirSolicitacao);
		Carona carona = buscaCaronaID(idCarona);
		Sugestao sugestao = new Sugestao(pontos, idSessao);
		carona.addSugestao(sugestao); // adiciona a sugestao na lista de
										// sugestoes da carona

		return sugestao.getIdSugestao();
	}

	public String responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) throws Exception {

		String idResposta = null;
		Sessao sessao = buscarSessaoId(idSessao);
		Carona carona = buscaCaronaID(idCarona);
		excecaoResponderPontoDeEncontro(pontos);

		if (sessao.getLogin().equals(carona.getDonoDaCarona().getLogin())) { // verifica se o login de quem ta logado eh igual ao do dono da carona
																		
			for (Sugestao sugestao : carona.getSugestoes()) {
				if (sugestao.getIdSugestao().equals(idSugestao)) { // verificase a sugestao eh igual a que eu procuro
																
					Resposta resp = new Resposta(pontos); // cria uma resposta
					idResposta = resp.getIdResposta();
					sugestao.addResposta(resp); // adiciona em uma lista a resposta dessa sugestao
					
										
				}

			}
		}

		return idResposta;
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws Exception {
		/*
		 * 1- Colocar solicitacao em carona 2- Return ID
		 */
		excecaoDesistirSolicitacao(desistirSolicitacao);
		Carona carona = buscaCaronaID(idCarona);
		Solicitacao solicitacao = new Solicitacao(idSessao, idCarona, ponto);
		excecaoSolicitacao(ponto,carona);

		for (Sugestao sugestao : carona.getSugestoes()) {
			for (Resposta resposta : sugestao.getlistaDeResposta()) {
				if (resposta.getPontos().contains(ponto)) { // verifica no mapa
															// de sugestoes e
															// respostas se
															// alguma resposta
															// contem o ponto
															// desejado para a
															// carona
					carona.addSolicitacao(solicitacao); // se o ponto esta no
														// mapa das sugestoes e
														// resposta adiciona
														// essa solicitacao numa
														// lista;
				}

			}

		}

		return solicitacao.getIdSolicitacao();
	}

	public String solicitarVaga(String idSessao, String idCarona) {
		Carona carona = buscaCaronaID(idCarona);
		Solicitacao solicitacao = new Solicitacao(idSessao, idCarona);

		carona.addSolicitacao(solicitacao);

		return solicitacao.getIdSolicitacao();
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo)
			throws Exception {

		Solicitacao solicitacao = buscaSolicitacao(idSolicitacao);
		Carona carona = buscaCaronaID(solicitacao.getIdCarona());
		String resposta = null;

		if (atributo.equals("origem")) {
			resposta = carona.getOrigem();
		} else if (atributo.equals("destino")) {
			resposta = carona.getDestino();
		} else if (atributo.equals("Dono da carona")) {
			resposta = carona.getDonoDaCarona().getNome();
		} else if (atributo.equals("Dono da solicitacao")) {
			Usuario usuario = buscaUsuario((buscarSessaoId(solicitacao
					.getIdSessao()).getLogin()));
			resposta = usuario.getNome();
		} else if (atributo.equals("Ponto de Encontro")) {
			for (Solicitacao sol : carona.getListaDeSolicitacao()) {
				if (sol.getIdSolicitacao().equals(idSolicitacao)) {
					resposta = sol.getPonto();
				}
			}
		}
		return resposta;
	}

	public Solicitacao buscaSolicitacao(String idSolicitacao) throws Exception {
		Solicitacao solicitacao = null;
		for (Carona carona1 : listaDeCaronas) {
			for (Solicitacao solicitacao1 : carona1.getListaDeSolicitacao()) {
				if (solicitacao1.getIdSolicitacao().equals(idSolicitacao)) {
					solicitacao = solicitacao1;
					break;
				}

			}
		}
		if (solicitacao == null) {
			throw new Exception("Solicitação inexistente");
		}
		return solicitacao;
	}

	public Sugestao buscaSugestao(String idSugestao, String idCarona) {
		Sugestao sugestaoEncontrada = null;
		Carona carona = buscaCaronaID(idCarona);
		for (Sugestao sugestao : carona.getSugestoes()) {
			if (sugestao.getIdSugestao().equals(idSugestao)) {
				sugestaoEncontrada = sugestao;
			}
		}
		return sugestaoEncontrada;
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,String idSolicitacao) throws Exception {
		Solicitacao solicitacao = buscaSolicitacao(idSolicitacao);
		Sessao sessao = buscarSessaoId(idSessao);
		Carona carona = buscaCaronaID(solicitacao.getIdCarona());

		if (sessao.getLogin().equals(carona.getDonoDaCarona().getLogin())) { // se o dono da carona bate com o id logado
																		      
			carona.setVagas(carona.getVagas() - 1); // diminiu uma vaga na carona
													
			carona.removeSolicitacao(solicitacao); // remove a solicitacao porque ja foi aceita
			carona.addPontoDeEncontro(solicitacao.getPonto()); // adiciona o ponto de encontro da solicitacao em uma lista de pontos de encontro para a carona
																
			for (Sugestao sugest : carona.getSugestoes()) {
				if (sugest.getIdSessao().equals(solicitacao.getIdSessao())) { // se a sugestao e a solicitacao foram feitas pelo mesmo usuario
				 														
				 carona.removeSugestao(sugest); // remove a sugestao da lista de sugestoes porque ela ja foi aceita											
                 break;
				}
			}
		}
	}

	public void aceitarSolicitacao(String idSessao, String idSolicitacao)
			throws Exception {
		// Quem aceita nao era para ser o Dono da carona?!?!?!?!?!?!?!?
		Solicitacao solicitacao = buscaSolicitacao(idSolicitacao);
		// Sessao sessao = buscarSessaoId(idSessao);
		Carona carona = buscaCaronaID(solicitacao.getIdCarona());
		carona.addSolicitacaoAceita(solicitacao);
		carona.removeSolicitacao(solicitacao);
		carona.setVagas(carona.getVagas() - 1);
		addhistoricoVagasEmCaronas(buscarSessaoId(solicitacao.getIdSessao())
				.getLogin(), carona.getIdDaCarona());

		List<String> pontoDeEncontro = new ArrayList<String>();
		if (solicitacao.getPonto() == null
				&& carona.getPontoDeEncontro().isEmpty()) {
			for (Sugestao sugestao : carona.getSugestoes()) {
				pontoDeEncontro.add(sugestao.getPontos());

			}
		}
		carona.setPontoDeEncontro(pontoDeEncontro);

	}

	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)
			throws Exception {
		Solicitacao solicitacao = buscaSolicitacao(idSolicitacao);
		Carona carona = buscaCaronaID(solicitacao.getIdCarona());
		carona.removeSolicitacao(solicitacao);

	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSugestao) { // precisa implementar do jeito certo....
		Sessao sessao = buscarSessaoId(idSessao);
		Carona carona = buscaCaronaID(idCarona);
		Sugestao sugestao = buscaSugestao(idSugestao, idCarona);
		if (sessao.getLogin().equals(carona.getDonoDaCarona().getLogin())) {
			desistirSolicitacao = true;
			if (sugestao != null) {
				carona.removeSugestao(sugestao);
			}
			// remover as coisas
			carona.removePontoDeEncontro(sugestao.getPontos());
			// sugestao.mapaDeResposta.remove(sugestao);
			carona.removeSugestao(sugestao);
			// carona.listaDeSolicitacao.remove(o);
			// carona.removeSolicitacao(sugestao.);
		}

	}

	public void excecaoDesistirSolicitacao(boolean desistirSolicitacao)
			throws Exception {

		if (desistirSolicitacao == true) {
			throw new Exception("Ponto Inválido");
		}

	}

	public void excecaoResponderPontoDeEncontro(String pontos) throws Exception {
		if (pontos.equals("") || pontos == null) {
			throw new Exception("Ponto Inválido");
		}
		

	}

	public void excecaoSolicitacao(String ponto, Carona carona)throws Exception {
		boolean pontoValido = false;
		for (Sugestao sugest : carona.getSugestoes()) {
			for (Resposta resposta : sugest.getlistaDeResposta()) {
			
			   if (resposta.getPontos().contains(ponto)) {
				   pontoValido = true;
				   break;
				
			 }
		  }
		}
		if (!pontoValido) {
			throw new Exception("Ponto Inválido");
		}
	}

	public Perfil visualizarPerfil(String idSessao, String login) throws Exception {
		Perfil retorno = null;
		Sessao sessao = buscarSessaoId(idSessao);
		if (sessao.getLogin().equals(login)) {
			retorno = buscaPerfil(login);
		}else{
			throw new Exception("Login inválido");
		}
		return retorno;
	}

	public void addCaronaNoHistorico(String login, String idCarona) {
		Perfil perfil = buscaPerfil(login);
		perfil.adicionaHistoricoCaronas(idCarona);
	}

	public void addhistoricoVagasEmCaronas(String login, String idCarona) {
		Perfil perfil = buscaPerfil(login);
		perfil.addhistoricoVagasEmCaronas(idCarona);
	}

	public Perfil buscaPerfil(String login) {
		Perfil retorno = null;
		Iterator<Perfil> itListaDePerfis = listaDePerfis.iterator();

		while (itListaDePerfis.hasNext()) {
			Perfil perfil = itListaDePerfis.next();
			if (perfil.getLogin().equals(login)) {
				retorno = perfil;
				break;

			}
		}

		return retorno;
	}

	public void reiniciarSistema() throws Exception {
	//	CriarXML cXml = new CriarXML();
	//	cXml.lerXMLUsuarios(listaDeUsuarios, listaDeCaronas);
		
		InterfaceXML interXML = new InterfaceXML("Perfis", listaDePerfis);
		interXML.loadData();
		interXML = new InterfaceXML("Usuarios", listaDeUsuarios);
		interXML.loadData();
		interXML = new InterfaceXML("Caronas", listaDeCaronas);
		interXML.loadData();

	}

	public String getCaronaUsuario(String idSessao, int indexCarona) {
		Sessao sessao = buscarSessaoId(idSessao);
		Usuario usuario = buscaUsuario(sessao.getLogin());
		String idCarona = usuario.getListaDeCaronasDoUsuario().get(indexCarona)
				.getIdDaCarona();

		return idCarona;
	}

	public String getAtributoPerfil(String login, String atributo)
			throws Exception {

		Perfil perfil = buscaPerfil(login);
		String resposta = null;

		if (atributo.equals("nome")) {
			resposta = perfil.getNome();
		} else if (atributo.equals("endereco")) {
			resposta = perfil.getEndereco();
		} else if (atributo.equals("email")) {
			resposta = perfil.getEmail();
		} else if (atributo.equals("historico de caronas")) {
			String historico = perfil.getHistoricoCaronas().toString();
			if (historico.equals("[]"))
				resposta = "";
			else
				resposta = historico;
		} else if (atributo.equals("historico de vagas em caronas")) {
			String historico = perfil.getHitoricoVagasEmCaronas().toString()
					.replace(" ", "");
			if (historico.equals("[]"))
				resposta = "";
			else
				resposta = historico;
		} else if (atributo.equals("caronas seguras e tranquilas")) {
			resposta = String.valueOf(perfil.getCaronasSeguras());
		} else if (atributo.equals("caronas que não funcionaram")) {
			resposta = String.valueOf(perfil.getCaronasNaoFuncionaram());
		} else if (atributo.equals("faltas em vagas de caronas")) {
			resposta = String.valueOf(perfil.getFaltasEmCaronas());
		} else if (atributo.equals("presenças em vagas de caronas")) {
			resposta = String.valueOf(perfil.getPresencaEmCaronas());
		}
		return resposta;
	}

	// OBS: esse metodo poe ser implementado usando o mapaDeCaronas existente só
	// que é bom mudar o mapaDeCaronas pra <String,String> para isso tem que
	// alterar alguns metodos,vou fazer isso depois.
	public String getTodasCaronasUsuario(String idSessao) {

		List<String> todasAsCaronas = new ArrayList<String>();
		Sessao sessao = buscarSessaoId(idSessao);
		Usuario usuario = buscaUsuario(sessao.getLogin());
		String strCaronas = null;
		for (Carona carona : usuario.getListaDeCaronasDoUsuario()) {
			todasAsCaronas.add(carona.getIdDaCarona());
		}

		strCaronas = todasAsCaronas.toString();
		strCaronas = strCaronas.replace("[", "{");
		strCaronas = strCaronas.replace("]", "}");
		strCaronas = strCaronas.replace(" ", "");

		return strCaronas;
	}

	public List<String> getSolicitacoesConfirmadas(String idSessao,
			String idCarona) {

		Sessao sessao = buscarSessaoId(idSessao);
		List<String> resp = new ArrayList<String>();
		Carona carona = buscaCaronaID(idCarona);
		if (isSessaoAberta(sessao.getId())) {
			for (Solicitacao solicitacao : carona
					.getListaDeSolicitacaoAceitas()) {
				resp.add(solicitacao.getIdSolicitacao());
			}
		}

		return resp;
	}

	public List<String> getSolicitacoesPendentes(String idCarona) {
		Carona carona = buscaCaronaID(idCarona);
		List<String> solicitacoesPendentes = new ArrayList<String>();

		for (Solicitacao solicitacao : carona.getListaDeSolicitacao()) {
			solicitacoesPendentes.add(solicitacao.getIdSolicitacao());
		}
		return solicitacoesPendentes;
	}

	public List<String> getPontosSugeridos(String idSessao, String idCarona) {
		Carona carona = buscaCaronaID(idCarona);
		Sessao sessao = buscarSessaoId(idSessao);
		List<String> pontosSugeridos = new ArrayList<String>();
		if (sessao.getLogin().equals(carona.getDonoDaCarona().getLogin())) { // se
																				// a
																				// sessao
																				// é
																				// do
																				// dono
																				// da
																				// carona

			for (Sugestao sugestao : carona.getSugestoes()) {
				pontosSugeridos.add(sugestao.getPontos());
			}

		}
		return pontosSugeridos;
	}

	public List<String> getPontosEncontro(String idSessao, String idCarona) {

		Sessao sessao = buscarSessaoId(idSessao);
		Carona carona = buscaCaronaID(idCarona);
		List<String> pontosEncontro = new ArrayList<String>();
		if (sessao.getLogin().equals(carona.getDonoDaCarona().getLogin())) {

			pontosEncontro = carona.getPontoDeEncontro();

		}
		return pontosEncontro;
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review) throws Exception {
		Sessao sessao = buscarSessaoId(idSessao);
		Carona carona = buscaCaronaID(idCarona);
		Perfil perfil = buscaPerfil(loginCaroneiro);

		boolean taNaCarona = false; // Verifica se o caroneiro ta na carona
		for (Solicitacao solicitacao : carona.getListaDeSolicitacaoAceitas()) {
			if (buscarSessaoId(solicitacao.getIdSessao()).getLogin().equals(
					loginCaroneiro)) {
				taNaCarona = true;
			}
		}

		if (taNaCarona) {// Se tiver...

			if (sessao.getLogin().equals(carona.getDonoDaCarona().getLogin())) {// Verifica
																				// se
																				// é
																				// o
																				// dono
																				// da
																				// carona
				if (review.equals("faltou")) { // Para poder da Review ou nao
					int faltas = perfil.getFaltasEmCaronas() + 1;
					perfil.setFaltasEmCaronas(faltas);
				} else if (review.equals("não faltou")) {
					int presenca = perfil.getPresencaEmCaronas() + 1;
					perfil.setPresencaEmCaronas(presenca);
				} else {
					throw new Exception("Opção inválida.");
				}
			}
		} else {
			throw new Exception("Usuário não possui vaga na carona.");
		}

	}

	public void zerarSistema() {
		listaDeCaronas.clear();
		listaDeSessoesAbertas.clear();
		listaDeUsuarios.clear();
		listaDePerfis.clear();
	//	encerrarSistema();

	}

	public static void main(String[] args) throws Exception {

		
		/*  SistemaDeCarona sistema = new SistemaDeCarona();
		  sistema.criarUsuario("Hudson", "123", "Hudson Daniel", "rua","hudson@gmail.com"); 
		  sistema.criarUsuario("Daniel", "1234","Hudson2", "rua", "12345"); 
          String idSessao =sistema.abrirSessao("Hudson", "123");
		  sistema.cadastrarCarona(idSessao, "Campina", "joao pessoa", "25/02/2013", "10:00", "3"); 
		  String idCarona = sistema.cadastrarCarona(idSessao, "joao pessoa", "campina","27/02/2013", "11:00", "3"); 
		  String idSugestao = sistema.sugerirPontoEncontro(idSessao, idCarona, "praca");
		  sistema.responderSugestaoPontoEncontro(idSessao, idCarona,idSugestao, "acude"); 
		  Carona carona = sistema.buscaCaronaID(idCarona);
		  
		  carona.addPontoDeEncontro("acude");
		  carona.addPontoDeEncontro("praca");*/
		 
		
		 
		 /* sistema.encerrarSistema(); 
		  sistema.reiniciarSistema();
		  sistema.zerarSistema();*/
		 

		// System.out.println(sistema.listaDePerfis.get(0).getHistoricoCaronas().get(0));
		
	//	 CriarXML cXMl = new CriarXML();
	//	 cXMl.criaXMLUsuarios(sistema.listaDeUsuarios);
		
		// System.out.println(sistema.ListaDeUsuarios.get(0).getListaDeCaronasDoUsuario().get(1).getSugestoes().get(0).getlistaDeResposta().get(0).getPontos());
		// System.out.println("tamanho da lista de carona: "+sistema.listaDeCaronas.size());

		/*
		 * SistemaDeCarona sistema = new SistemaDeCarona(); //Cria os 3 Usuarios
		 * sistema.criarUsuario("mark", "senham", "nomeM", "enderecoM",
		 * "emailM"); sistema.criarUsuario("bill", "senhaB", "nomeB",
		 * "enderecoB", "emailB"); sistema.criarUsuario("vader", "senhaV",
		 * "nomeV", "enderecoV", "emailV");
		 * 
		 * String idSessaoMark = sistema.abrirSessao("mark", "senham"); //Abre
		 * Mark //Cadastra 2 Caronas em Mark String idCarona4 =
		 * sistema.cadastrarCarona(idSessaoMark, "origemM", "destinoM",
		 * "12/05/2012", "12:00", "3"); String idCarona5 =
		 * sistema.cadastrarCarona(idSessaoMark, "origemM2", "destinoM2",
		 * "12/06/2012", "12:00", "3");
		 * 
		 * String idSessaoBill = sistema.abrirSessao("bill", "senhaB");//Abre
		 * Bill //Bill Solicita vaga nas 2 em Mark String solicitacao4 =
		 * sistema.solicitarVaga(idSessaoBill, idCarona4); String solicitacao5 =
		 * sistema.solicitarVaga(idSessaoBill, idCarona5);
		 * 
		 * //Mark.. aceita a solicitacao de Bill (Ta errado) //Nao entra no if
		 * Vê la coloca um syso sistema.aceitarSolicitacao(idSessaoMark,
		 * solicitacao4); sistema.aceitarSolicitacao(idSessaoMark,
		 * solicitacao5);
		 * 
		 * //deveria sair uma lista com o historico Carona1 Carona
		 * //System.out.println(sistema.getAtributoPerfil("bill",
		 * "historico de vagas em caronas") +" aqui");
		 * System.out.println(sistema.getAtributoPerfil("bill",
		 * "faltas em vagas de caronas"));
		 * sistema.reviewVagaEmCarona(idSessaoMark, idCarona4, "bill",
		 * "faltou"); System.out.println(sistema.getAtributoPerfil("bill",
		 * "faltas em vagas de caronas"));
		 */
	}

}
