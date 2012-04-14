package sistema;

import java.util.*;

public class SistemaDeCarona {

	/**
	 * @param args
	 */
	private List<Usuario> ListaDeUsuarios = new ArrayList<Usuario>();
	private List<Carona> listaDeCaronas = new ArrayList<Carona>();
	private List<Sessao> listaDeSessoesAbertas = new ArrayList<Sessao>();
	private Map<String, List<Carona>> mapaDeCaronas = new HashMap<String, List<Carona>>();

	public SistemaDeCarona() {
	}

/**
 * Criar Usuario
 * @param login
 * @param senha
 * @param nome
 * @param endereco
 * @param email
 * @throws Exception
 */
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {

		excecaoCriarUsuario(login, senha, nome, endereco, email);

		Usuario novoUsuario = new Usuario(login, senha, nome, endereco, email);
		ListaDeUsuarios.add(novoUsuario);

	}

/**
 * AbrirSessao
 * @param login
 * @param senha
 * @return
 * @throws Exception
 */
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

			} else {

				throw new Exception("Login inválido");
			}

		} else {

			throw new Exception("Usuário inexistente");
		}

		return id;

	}

	/**
	 * Pegar Atributo de Usuario
	 * @param login
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * Pegar Atributo da Carona;
	 * @param idDaCarona
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoCarona(String idDaCarona, String atributo)
			throws Exception {
		String saida = null;
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

		// aceita o primeiro que achar e depois sai.
		return saida;
	}


	
/**
 * Busca Carona pelo id
 * @param idDaCarona
 * @return
 */
	public Carona buscaCaronaID(String idDaCarona) {

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


	public List<Usuario> getUsuarios() {
		return ListaDeUsuarios;
	}

	/**
	 * Busca usuario e retorna um Usuario a partir de seu login
	 * @param login
	 * @return
	 */
	public Usuario buscaUsuario(String login) {
		for (int i = 0; i < ListaDeUsuarios.size(); i++) {
			if (ListaDeUsuarios.get(i).getLogin().equals(login)) {
				return ListaDeUsuarios.get(i);
			}
		}
		return null;
	}
/**
 * Verifica se a sessão ta Aberta
 * @param id
 * @return
 */
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
	
/**
 * Cadastrar Carona
 * @param idDaSessao
 * @param origem
 * @param destino
 * @param data
 * @param hora
 * @param vagas
 * @return
 * @throws Exception
 */
	public String cadastrarCarona(String idDaSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws Exception {
		excecaoDeCriacaoDeCarona(idDaSessao, origem, destino, data, hora, vagas);
		int vagasInt = Integer.parseInt(vagas);
		
		Sessao sessao = buscarSessaoId(idDaSessao);
		Usuario usuario = buscaUsuario(sessao.getLogin());
		Carona novaCarona = new Carona(origem, destino, data, hora, vagasInt, usuario);
		listaDeCaronas.add(novaCarona);

		if (mapaDeCaronas.containsKey(idDaSessao)) {
			mapaDeCaronas.get(idDaSessao).add(novaCarona);
		} else {
			mapaDeCaronas.put(idDaSessao, new ArrayList<Carona>());
			mapaDeCaronas.get(idDaSessao).add(novaCarona);
		}

		return novaCarona.getIdDaCarona();

	}

	/**
	 * Localizar Carona;
	 * @param idDaSessao
	 * @param origem
	 * @param destino
	 * @return
	 * @throws Exception
	 */
	public String localizarCarona(String idDaSessao, String origem,
			String destino) throws Exception {

		excecaoLocalizarCarona(idDaSessao, origem, destino);
		List<String> caronasEncontradas = new ArrayList<String>();
		String strCaronas = null;

		for (String chave : mapaDeCaronas.keySet()) {

			for (Carona carona : mapaDeCaronas.get(chave)) {

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

		return strCaronas.replace(" ", "");
	}

	
/**
 * Busca Sessa pelo IdDaSessao
 * @param idDaSessao
 * @return
 */
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
	
/**
 * Pega Trajeto
 * @param idDaCarona
 * @return
 * @throws Exception
 */
	public String getTrajeto(String idDaCarona) throws Exception {
		excecaoGetTrajeto(idDaCarona);
		return buscaCaronaID(idDaCarona).getOrigem() + " - "
				+ buscaCaronaID(idDaCarona).getDestino();
	}

	
/**
 * Pega Atributo Carona
 * @param idDaCarona
 * @return
 * @throws Exception
 */
	public String getCarona(String idDaCarona) throws Exception {
		excecaoGetCarona(idDaCarona);
		Carona carona = buscaCaronaID(idDaCarona);
		// João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59
		return carona.getOrigem() + " para " + carona.getDestino()
				+ ", no dia " + carona.getData() + ", as " + carona.getHora();
	}
	/**
	 * Encerra Sessao Aberta
	 * @param login
	 */
	public void encerrarSessao(String login){
		Sessao sessao = null;
		for(Sessao sessao1 : listaDeSessoesAbertas){
				listaDeSessoesAbertas.remove(sessao1.getLogin().equals(login));
		}
	
	}
	/**
	 * Sugere ponto de Encontro
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @return
	 */
	public String sugerirPontoEncontro(String idSessao, String idCarona, String pontos){
		Sugestao sugestao = new Sugestao(idSessao, idCarona, pontos);
		buscaCaronaID(idCarona).addSugestoesEncontro(sugestao);
		return sugestao.getIdSessao();
	}

	/**
	 * responder Sugestao de Ponto
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 * @param pontos
	 * @throws Exception
	 */
	public void responderSugestaoPontoEncontro(String idSessao, String idCarona, String idSugestao, String pontos) throws Exception{
		Sessao sessao = buscarSessaoId(idSessao);
		Carona carona = buscaCaronaID(idCarona);
		if(carona.equals(null)){
			throw new Exception("Carona Invalida");
		}
		for(Carona carona1 :mapaDeCaronas.get(idSessao)){
			if(carona1.getIdDaCarona().equals(idCarona)){
				carona.setPontoDeEncontro(pontos);
			}
			
		}

	}
	
	/**
	 * Solicitar Vaga no ponto de Encontro
	 * @param idSessao
	 * @param idCarona
	 * @param ponto
	 * @return
	 * @throws Exception
	 */
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona , String ponto) throws Exception{
		Carona carona = buscaCaronaID(idCarona);
		Solicitacao solicitacao = new Solicitacao(idSessao, ponto, carona);
		if(carona.equals(null)){
			throw new Exception("Carona Invalida");
		}else{
			carona.addSolicitacaoVagas(solicitacao);
		}
		return solicitacao.getId();
	}
	
	/**
	 * Pegar atributo de Solicitacao
	 * @param idSolicitacao
	 * @param atributo
	 * @return
	 */

	public String getAtributoSolicitacao(String idSolicitacao, String atributo){
		Solicitacao solicitacao = buscaSolicitacao(idSolicitacao);
			if(atributo.equals("origem")){
				return solicitacao.getCarona().getOrigem();
			}
			else if(atributo.equals("destino")){
				return solicitacao.getCarona().getDestino();
			}
			else if(atributo.equals("Dono da carona")){
				return solicitacao.getCarona().getDonoCarona().getNome();
			}
			else if(atributo.equals("Dono da solicitacao")){
				Sessao sessao = buscarSessaoId(solicitacao.getIdDoSolicitador());
				Usuario usuario = buscaUsuario(sessao.getLogin());
				return usuario.getNome();
			}
			else if(atributo.equals("Ponto de Encontro")){
				return solicitacao.getPonto();
			}else{
				return null;
			}
	}
	
	public Solicitacao buscaSolicitacao(String idSolicitacao){
		for(Carona carona1: listaDeCaronas){
			for(Solicitacao solicitacao1: carona1.getSolicitacaoVagas()){
				if(solicitacao1.getId().equals(idSolicitacao)){
					return solicitacao1;
				}
			}
		}
		return null;
	}
	
	/**
	 * Encerrar Sistema
	 * 
	 * @throws Throwable
	 */
	public void encerrarSistema() throws Throwable {
		this.finalize();
	}
	
	//---------------------------------------------------------------Sistema Acima---------------------------------------------------//
	/**
	 * Execoes 
	 * @param login
	 * @param atributo
	 * @throws Exception
	 */
	// Colocar em uma classe separada;
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
	
	public void excecaoGetCarona(String idDaCarona) throws Exception {
		if (idDaCarona == null) {
			throw new Exception("Carona Inválida");

		}
		if (idDaCarona.equals("") || buscaCaronaID(idDaCarona) == null) {

			throw new Exception("Carona Inexistente");
		}
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
		
		for (int i = 0; i < ListaDeUsuarios.size(); i++) {
			if (ListaDeUsuarios.get(i).getLogin().equals(login)) {
				throw new Exception("Já existe um usuário com este login");
			}
			if (ListaDeUsuarios.get(i).getEmail().equals(email)) {
				throw new Exception("Já existe um usuário com este email");
			}
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
		
		if (data == null || data.equals("") || !Carona.isDataValida(data)) {
			throw new Exception("Data inválida");
		}
		
		if (hora == null || hora.equals("") || !Carona.horaValida(hora)) {
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
				&& !atributo.equals("data") && !atributo.equals("vagas")) {
			throw new Exception("Atributo inexistente");
		}
	}

	public static void main(String[] args) throws Exception {
	 SistemaDeCarona sc = new SistemaDeCarona();
	 sc.criarUsuario("rennanhm", "senha1", "Renna Henrique", "Rua tal do tal", "rennanhm@gmail.com");
	 sc.criarUsuario("bill", "billsenha", "Biludo", "rua macaiba", "bil@gmail.com");
	String idCarona = sc.cadastrarCarona(sc.abrirSessao("rennanhm", "senha1"), "origem1", "destino1", "25/07/2012", "13:00", "3");
	 sc.encerrarSessao("rennanhm");
	 String soliciatacao = sc.solicitarVagaPontoEncontro(sc.abrirSessao("bill", "billsenha"), idCarona, "Açude");
	System.out.println(sc.getAtributoSolicitacao(soliciatacao, "Dono da solicitacao")); 
	 
	}
}
