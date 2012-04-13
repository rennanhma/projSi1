package easy;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import sistema.*;

public class Easy {
	SistemaDeCarona sistema = new SistemaDeCarona();
    
	public void zerarSistema() {
		sistema = new SistemaDeCarona();
	}

	public void criarUsuario(String login, String senha, String nome,String endereco, String email) throws Exception {
            sistema.criarUsuario(login, senha, nome, endereco, email);
	}

	public void encerrarSistema() throws Throwable {

		sistema.encerrarSistema();
	}
	
	
	public String abrirSessao(String login,String senha) throws Exception{
		return sistema.abrirSessao(login, senha);
	}
	public String getAtributoUsuario(String login, String atributo) throws Exception{
	    return sistema.getAtributoUsuario(login, atributo);
	}
	
	public Usuario buscaUsuario(String login){
		return sistema.buscaUsuario(login);
	}
   
	public String localizarCarona(String idDaSessao,String origem,String destino) throws Exception{
		
		return sistema.localizarCarona(idDaSessao,origem,destino);
	}
	
	public String cadastrarCarona(String idDaSessao, String origem, String destino, String data, String hora, String vagas) throws Exception
	{
		return sistema.cadastrarCarona(idDaSessao, origem, destino, data, hora, vagas);
	}
	
	public String getAtributoCarona(String idDaSessao, String atributo) throws Exception
	{
		return sistema.getAtributoCarona(idDaSessao, atributo);
	}
	
	public String getTrajeto(String id) throws Exception
	{
		return sistema.getTrajeto(id);
	}
	
	public String getCarona(String id) throws Exception
	{
		return sistema.getCarona(id);
	}

	public void encerrarSessao(String login){
		sistema.encerrarSessao(login);
	}
	
	public String sugerirPontoEncontro (String idSessao, String idCarona, String pontos){
		return sistema.sugerirPontoEncontro(idSessao, idCarona, pontos);
	}
	
	public void responderSugestaoPontoEncontro(String idSessao, String idCarona, String  idSugestao, String pontos) throws Exception{
		sistema.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao, pontos);
	}
	
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona, String ponto) throws Exception{
		return sistema.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}
	
	public String getAtributoSolicitacao(String idSolicitacao, String atributo){
		return sistema.getAtributoSolicitacao(idSolicitacao, atributo);
	}
	
	
}
