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

	public void encerrarSistema() {

		sistema.encerrarSistema();
	}
	
	
	public void abrirSessao(String login,String senha) throws Exception{
		sistema.abrirSessao(login, senha);
	}
	public String getAtributoUsuario(String login, String atributo) throws Exception{
	    return sistema.getAtributoUsuario(login, atributo);
	}
	
	public Usuario buscaUsuario(String login){
		return sistema.buscaUsuario(login);
	}
   
	public Map<String, List> localizarCarona(String idDaSessao,String origem,String destino){
		
		return sistema.localizarCarona(idDaSessao,origem,destino);
	}
	
	public String cadastrarCarona(String idDaSessao, String origem, String destino, String data, String hora, int vagas)
	{
		return sistema.cadastrarCarona(idDaSessao, origem, destino, data, hora, vagas);
	}
	// At line 22: Unknown command: carona1ID=cadastrarCarona idSessao= origem="Campina Grande" destino="João Pessoa" data=23/06/2012 hora=16:00 vagas=3
	/*public cadastraCarona(String idDaSessao, String origem, String destino, String data, String hora, String vagas){
		
	}*/

}
