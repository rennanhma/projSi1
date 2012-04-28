package easy;

import java.util.List;

import sistema.*;

public class Fachada {
	SistemaDeCarona sistema = new SistemaDeCarona();

	public void zerarSistema() {
		sistema = new SistemaDeCarona();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		sistema.criarUsuario(login, senha, nome, endereco, email);
	}

	public void encerrarSistema() throws Throwable {

		sistema.encerrarSistema();
	}

	public String abrirSessao(String login, String senha) throws Exception {
		return sistema.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		return sistema.getAtributoUsuario(login, atributo);
	}

	public Usuario buscaUsuario(String login) {
		return sistema.buscaUsuario(login);
	}

	public String localizarCarona(String idDaSessao, String origem,
			String destino) throws Exception {

		return sistema.localizarCarona(idDaSessao, origem, destino);
	}

	public String cadastrarCarona(String idDaSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws Exception {
		return sistema.cadastrarCarona(idDaSessao, origem, destino, data, hora,
				vagas);
	}

	public String getAtributoCarona(String idDaSessao, String atributo)
			throws Exception {
		return sistema.getAtributoCarona(idDaSessao, atributo);
	}

	public String getTrajeto(String id) throws Exception {
		return sistema.getTrajeto(id);
	}

	public String getCarona(String id) throws Exception {
		return sistema.getCarona(id);
	}

	public void encerrarSessao(String login) {
		sistema.encerrarSessao(login);
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws Exception {
		return sistema.sugerirPontoEncontro(idSessao, idCarona, pontos);

	}

	public String responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) throws Exception {
		return sistema.responderSugestaoPontoEncontro(idSessao, idCarona,
				idSugestao, pontos);
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws Exception {
		return sistema.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}
	
	
	public String solicitarVaga(String idSessao,String idCarona){
		
		return sistema.solicitarVaga(idSessao, idCarona);
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo)
			throws Exception {
		return sistema.getAtributoSolicitacao(idSolicitacao, atributo);
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws Exception {
		sistema.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}
	
	public void aceitarSolicitacao(String idSessao,String idSolicitacao) throws Exception{
		 sistema.aceitarSolicitacao(idSessao, idSolicitacao);
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSugestao) {
		sistema.desistirRequisicao(idSessao, idCarona, idSugestao);
	}
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws Exception
	{
		sistema.rejeitarSolicitacao(idSessao, idSolicitacao);
		
	}
	
    public Perfil visualizarPerfil(String idSessao, String login)
    {
    	return sistema.visualizarPerfil(idSessao, login);
    }
    
    public String getAtributoPerfil(String login, String atributo) throws Exception
    {
    	return sistema.getAtributoPerfil(login, atributo);
    }
    
    public void reiniciarSistema() throws Exception{
    	
    	sistema.reiniciarSistema();
    }
    
    public String getCaronaUsuario(String idSessao, int indexCarona){
    	
    	return sistema.getCaronaUsuario(idSessao, indexCarona-1);
    }
    
    
    public String getTodasCaronasUsuario(String idSessao){
    	
    	return sistema.getTodasCaronasUsuario(idSessao);
    }
    
    public List<String> getSolicitacoesConfirmadas(String idSessao, String idCarona){
    	
    	return sistema.getSolicitacoesConfirmadas(idSessao, idCarona);
    }
    
    public List<String> getSolicitacoesPendentes(String idCarona){
    	
    	return sistema.getSolicitacoesPendentes(idCarona);
    }
    
    public List<String> getPontosSugeridos(String idSessao, String idCarona){
    	
    	return sistema.getPontosSugeridos(idSessao, idCarona);
    }
    
    public List<String> getPontosEncontro(String idSessao, String idCarona){
    	
    	return sistema.getPontosEncontro(idSessao, idCarona);
    }
    public void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review) throws Exception {
    	sistema.reviewVagaEmCarona(idSessao, idCarona, loginCaroneiro, review);
    }


}
