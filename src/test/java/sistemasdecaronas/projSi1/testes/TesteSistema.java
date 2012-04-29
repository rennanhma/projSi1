package sistemasdecaronas.projSi1.testes;

import static org.junit.Assert.*;




import org.junit.*;

import sistemadecaronas.projSi1.sistema.Carona;
import sistemadecaronas.projSi1.sistema.SistemaDeCarona;
import sistemadecaronas.projSi1.sistema.Solicitacao;
import sistemadecaronas.projSi1.sistema.Sugestao;

public class TesteSistema {

	SistemaDeCarona sistema;
	String sessaoMark;
	String sessaoSteve;
	String sessaoBill;
	String idCarona4;
	String idCarona5;

	@Before
	public void antes() throws Exception {
		sistema = new SistemaDeCarona();
		sistema.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		sistema.criarUsuario("steve", "5t3v3", "Steven Paul Jobs",
				"Palo Alto, California", "jobs@apple.com");
		sistema.criarUsuario("bill", "severino", "William Henry Gates III",
				"Medina, Washington", "bil@apple.com");
		try {
			sistema.criarUsuario("", "sda", "fasdf", "Pfa", "xcv@apple.com");
		} catch (Exception e) {
			assertEquals("Login inválido", e.getMessage());

		}
		try {
			sistema.criarUsuario(null, "fas", "afa", "Palo dsia",
					"asd@apple.com");
		} catch (Exception e) {
			assertEquals("Login inválido", e.getMessage());
		}
		sessaoMark = sistema.abrirSessao("mark", "m@rk");
		sessaoSteve = sistema.abrirSessao("steve", "5t3v3");
		sessaoBill = sistema.abrirSessao("bill", "severino");

		idCarona4 = sistema.cadastrarCarona(sessaoMark, "Campina Grande",
				"Joao Pessoa", "12/06/2012", "14:00", "3");
		idCarona5 = sistema.cadastrarCarona(sessaoMark, "Lagoa Seca", "Recife",
				"12/07/2013", "23:00", "2");

	}

	@After
	public void depois() throws Exception {
	}

	@Test
	public void testeLista() throws Exception {
		// adicionou os 3 mark , steave e bill
		assertEquals(3, sistema.getUsuarios().size());

	}

	@Test
	public void testeAbrirSessao() throws Exception {
		sistema.abrirSessao("mark", "m@rk");
		try {
			sistema.abrirSessao("", "m@rk");
		} catch (Exception e) {
			assertEquals("Login inválido", e.getMessage());
		}
		try {
			sistema.abrirSessao("mark", "");
		} catch (Exception e) {
			assertEquals("Senha inválida", e.getMessage());
		}

		try {
			sistema.abrirSessao("asd", "123");
		} catch (Exception e) {
			assertEquals("Usuário inexistente", e.getMessage());
		}
		try {
			sistema.abrirSessao("mark", "123");
		} catch (Exception e) {
			assertEquals("Login inválido", e.getMessage());
		}
	}

	@Test
	public void testeGetAtributoUsuario() throws Exception {
		assertEquals("Mark Zuckerberg",
				sistema.getAtributoUsuario("mark", "nome"));
		assertEquals("Palo Alto, California",
				sistema.getAtributoUsuario("mark", "endereco"));
		try {
			sistema.getAtributoUsuario("mark", "nada");
		} catch (Exception e) {
			assertEquals("Atributo inexistente", e.getMessage());
		}

		try {
			sistema.getAtributoUsuario("mark", "");
		} catch (Exception e) {
			assertEquals("Atributo inválido", e.getMessage());
		}
	}

	@Test
	public void testaCadastrarCarona() throws Exception {
		try {
			sistema.cadastrarCarona("123", "Campina Grande", "Joao Pessoa",
					"12/06/2012", "14:00", "3");
		} catch (Exception e) {
			assertEquals("Sessão inexistente", e.getMessage());
		}
		try {
			sistema.cadastrarCarona("", "Campina Grande", "Joao Pessoa",
					"12/06/2012", "14:00", "3");
		} catch (Exception e) {
			assertEquals("Sessão inválida", e.getMessage());
		}
		try {
			sistema.cadastrarCarona(sessaoMark, "", "Joao Pessoa",
					"12/06/2012", "14:00", "3");
		} catch (Exception e) {
			assertEquals("Origem inválida", e.getMessage());
		}
		try {
			sistema.cadastrarCarona(sessaoMark, "Campina Grande", "",
					"12/06/2012", "14:00", "3");
		} catch (Exception e) {
			assertEquals("Destino inválido", e.getMessage());
		}
		try {
			sistema.cadastrarCarona(sessaoMark, "Campina Grande",
					"Joao Pessoa", "30/25/2012", "14:00", "3");
		} catch (Exception e) {
			assertEquals("Data inválida", e.getMessage());
		}
		try {
			sistema.cadastrarCarona(sessaoMark, "Campina Grande",
					"Joao Pessoa", "12/06/2012", "32:00", "3");
		} catch (Exception e) {
			assertEquals("Hora inválida", e.getMessage());
		}
		try {
			sistema.cadastrarCarona(sessaoMark, "Campina Grande",
					"Joao Pessoa", "12/06/2012", "14:00", "");
		} catch (Exception e) {
			assertEquals("Vaga inválida", e.getMessage());
		}
		sistema.cadastrarCarona(sessaoMark, "origemM", "destino", "12/07/2012",
				"14:00", "3");
	}

	@Test
	public void testaGetAtributoCarona() throws Exception {
		assertEquals("Campina Grande",
				sistema.getAtributoCarona(idCarona4, "origem"));
		assertEquals("Joao Pessoa",
				sistema.getAtributoCarona(idCarona4, "destino"));
		assertEquals("3", sistema.getAtributoCarona(idCarona4, "vagas"));
	
		 assertEquals("", sistema.getAtributoCarona(idCarona4,
		 "Ponto de Encontro"));
		 
		 Carona carona = sistema.buscaCaronaID(idCarona4);
		 carona.addPontoDeEncontro("parque da crianca");
		 
		 assertEquals("parque da crianca", sistema.getAtributoCarona(idCarona4,
				 "Ponto de Encontro"));
		 
		 carona.addPontoDeEncontro("acude");
		 
		 assertEquals("parque da crianca, acude", sistema.getAtributoCarona(idCarona4,
				 "Ponto de Encontro"));
		 
	}

	@Test
	public void testaLocalizarCarona() throws Exception {
		assertEquals("{}", sistema.localizarCarona(sessaoMark, "São Francisco",
				"Palo Alto"));
	}

	@Test
	public void testaGetTrajeto() throws Exception {
		assertEquals("Campina Grande - Joao Pessoa",
				sistema.getTrajeto(idCarona4));
		try {
			sistema.getTrajeto(null);
		} catch (Exception e) {
			assertEquals("Trajeto Inválido", e.getMessage());
		}
		try {
			sistema.getTrajeto("");
		} catch (Exception e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}
		try {
			sistema.getTrajeto("dcasdas");
		} catch (Exception e) {
			assertEquals("Trajeto Inexistente", e.getMessage());
		}

	}

	@Test
	public void testaGetCarona() throws Exception {
		assertEquals(
				"Campina Grande para Joao Pessoa, no dia 12/06/2012, as 14:00",
				sistema.getCarona(idCarona4));
		try {
			sistema.getCarona(null);
		} catch (Exception e) {
			assertEquals("Carona Inválida", e.getMessage());
		}
		try {
			sistema.getCarona("");
		} catch (Exception e) {
			assertEquals("Carona Inexistente", e.getMessage());
		}
	}

	@Test
	public void sugerirPontoEncontro() throws Exception {
		Carona carona = sistema.buscaCaronaID(idCarona4);
		assertEquals(0, carona.getSugestoes().size());
		sistema.sugerirPontoEncontro(sessaoBill, idCarona4, "acude");
		assertEquals(1, carona.getSugestoes().size());
	}

	@Test
	public void testaResponderSugestaoPontoEncontro() throws Exception {
		String idSugestao = sistema.sugerirPontoEncontro(sessaoBill, idCarona4,
				"acude");
		Sugestao sugestao = sistema.buscaSugestao(idSugestao, idCarona4);
		assertEquals(0, sugestao.getlistaDeResposta().size());
		sistema.responderSugestaoPontoEncontro(sessaoMark, idCarona4,
				idSugestao, "Parque Crianca");
		assertEquals(1, sugestao.getlistaDeResposta().size());
	}

	@Test
	public void testaSolicitarVagaPontoEncontro() throws Exception {
	    /*
		  e ele pode solicitar vaga em acude tranquilo que é um ponto sugerido
		  nao é um ponto aceito.. nao entendi
		 */
		String idSugestao = sistema.sugerirPontoEncontro(sessaoBill, idCarona4,
				"acude");
		sistema.responderSugestaoPontoEncontro(sessaoMark, idCarona4,
				idSugestao, "Parque Crianca");
		sistema.solicitarVagaPontoEncontro(sessaoBill, idCarona4, "Parque Crianca");
	}

	@Test
	public void testaSolicitarVaga() {
		sistema.solicitarVaga(sessaoBill, idCarona4);
		Carona carona = sistema.buscaCaronaID(idCarona4);
		assertEquals(1, carona.getListaDeSolicitacao().size());
	}

	@Test
	public void testaGetAtributoSolicitacao() throws Exception {
		// origem, destino, Dono da carona, Dono da solicitacao, Ponto de
		// Encontro
		String idSolicitacao = sistema.solicitarVaga(sessaoBill, idCarona4);
		assertEquals("Campina Grande",
				sistema.getAtributoSolicitacao(idSolicitacao, "origem"));
		assertEquals("Joao Pessoa",
				sistema.getAtributoSolicitacao(idSolicitacao, "destino"));
		assertEquals("Mark Zuckerberg",
				sistema.getAtributoSolicitacao(idSolicitacao, "Dono da carona"));
		assertEquals("William Henry Gates III", sistema.getAtributoSolicitacao(
				idSolicitacao, "Dono da solicitacao"));
		/*
		 * [BUG] como eu seto o ponto de encontro?
		 */
		Solicitacao solicitacao = sistema.buscaSolicitacao(idSolicitacao);
		
		assertEquals(null, sistema.getAtributoSolicitacao(idSolicitacao,
				"Ponto de Encontro"));
		
		solicitacao.setPonto("Acude");
		
		assertEquals(null, sistema.getAtributoSolicitacao(idSolicitacao,
				"Acude"));

	}

	@Test
	public void testaAceitarSolicitacaoPontoEncontro() throws Exception {
	
		String idSugestao = sistema.sugerirPontoEncontro(sessaoBill, idCarona4, "acude");
		sistema.responderSugestaoPontoEncontro(sessaoMark, idCarona4, idSugestao, "acude");
		String idSolicitacao = sistema.solicitarVagaPontoEncontro(sessaoBill, idCarona4, "acude");
		sistema.aceitarSolicitacaoPontoEncontro(sessaoMark, idSolicitacao);
	}
	

}
