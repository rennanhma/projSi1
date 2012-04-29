package persistencia;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.crypto.XMLCryptoContext;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import sistema.Carona;

import sistema.Resposta;
import sistema.Solicitacao;
import sistema.Sugestao;
import sistema.Usuario;

public class CriarXML {

	public void criaXMLUsuarios(List<Usuario> listaDeUsuarios){
		
		Element todosOsUsuarios = new Element("TodosOsUsuarios");
				
		for (Usuario usuario2 : listaDeUsuarios) {
			
			
			Element usuario = new Element("Usuario");
			Element login = new Element("Login");
			Element senha = new Element("Senha");
			Element nome = new Element("Nome");
			Element email =  new Element("Email");
			Element endereco = new Element("Endereco");
			Element id = new Element("Id");
			
			
			login.setText(usuario2.getLogin());
			senha.setText(usuario2.getSenha());
			nome.setText(usuario2.getNome());
			email.setText(usuario2.getEmail());
			endereco.setText(usuario2.getEndereco());
			id.setText(usuario2.getId());

			
			
			usuario.addContent(login);
			usuario.addContent(senha);
			usuario.addContent(nome);
			usuario.addContent(email);
			usuario.addContent(endereco);
			usuario.addContent(id);
			
			Element listaDeCaronasDoUsuario = new Element("CaronasDoUsuario");
			
			for (Carona carona2 : usuario2.getListaDeCaronasDoUsuario()) {
				
				Element carona = new Element("Carona");
				Element origem = new Element("Origem");
				Element destino = new Element("Destino");
				Element data = new Element("Data");
				Element hora = new Element("Hora");
				Element idCarona =  new Element("IdCarona");
				Element vagas =  new Element("Vagas");
				
				origem.setText(carona2.getOrigem());
				destino.setText(carona2.getDestino());
				data.setText(carona2.getData());
				hora.setText(carona2.getHora());
				idCarona.setText(carona2.getIdDaCarona());
				vagas.setText(carona2.getVagas()+"");
				
				carona.addContent(origem);
				carona.addContent(destino);
				carona.addContent(data);
				carona.addContent(hora);
				carona.addContent(idCarona);
				carona.addContent(vagas);	
				
				
				Element listaDeSugestoes = new Element("listaDeSugestoes");
				
				for (Sugestao sugestao : carona2.getSugestoes()) {
					Element sugestao2 = new Element("Sugestao");
					Element respostasDaSugestao = new Element("RespostaDaSugestao");
					Element pontos = new Element("Pontos");
					Element idSessao = new Element("idSessao");
					Element idSugestao = new Element("IdSugestao");
					
					pontos.setText(sugestao.getPontos());
					idSessao.setText(sugestao.getIdSessao());
					idSugestao.setText(sugestao.getIdSugestao());
					
					for (Resposta respostas : sugestao.listaDeResposta) {
						
						Element resposta = new Element("Resposta");
						Element ponto = new Element("Ponto");
						Element idResposta = new Element("IdResposta");
						
						ponto.setText(respostas.getPontos());
						idResposta.setText(respostas.getIdResposta());
						
						resposta.addContent(ponto);
						resposta.addContent(idResposta);
						
						respostasDaSugestao.addContent(resposta);
						
						sugestao2.addContent(pontos);
						sugestao2.addContent(idSessao);
						sugestao2.addContent(idSugestao);
						sugestao2.addContent(respostasDaSugestao);
						
					}
					
					listaDeSugestoes.addContent(sugestao2);
	
					
				}

				carona.addContent(listaDeSugestoes);
				
			   Element pontosDeEncontro = new Element("PontosDeEncontro");
			   
			   for (String ponto : carona2.getPontoDeEncontro()) {
				  
				   Element pontoDeEncontro = new Element("PontoDeEncontro");
				   
				   pontoDeEncontro.setText(ponto);
				   
				   pontosDeEncontro.addContent(pontoDeEncontro);
			}
			   
			   carona.addContent(pontosDeEncontro);
			   
		   Element solicitacoes = new Element("Solicitacoes");
		   
		   for (Solicitacao solicitacao : carona2.getListaDeSolicitacao()) {
			    
			   Element idSessao = new Element("IdSessao");
			   Element idCarona2 = new Element("IdCarona");
			   Element ponto = new Element("Ponto");
			   
			   idSessao.setText(solicitacao.getIdSessao());
			   idCarona2.setText(solicitacao.getIdCarona());
			   ponto.setText(solicitacao.getPonto());
			   
			   solicitacoes.addContent(idSessao);
			   solicitacoes.addContent(idCarona2);
			   solicitacoes.addContent(ponto);
			   
			   
		    
		   
		   }
		   
		   carona.addContent(solicitacoes);
		   
		   Element solicitacoesAceitas = new Element("SolicitacoesAceitas");
           for (Solicitacao solicitacao : carona2.getListaDeSolicitacaoAceitas()) {
				
        	   Element idSessao = new Element("IdSessao");
			   Element idCarona2 = new Element("IdCarona");
			   Element ponto = new Element("Ponto");
			   
			   idSessao.setText(solicitacao.getIdSessao());
			   idCarona2.setText(solicitacao.getIdCarona());
			   ponto.setText(solicitacao.getPonto());
			   
			   solicitacoesAceitas.addContent(idSessao);
			   solicitacoesAceitas.addContent(idCarona2);
			   solicitacoesAceitas.addContent(ponto);
			   
        	   
			}
           
           carona.addContent(solicitacoesAceitas);
            listaDeCaronasDoUsuario.addContent(carona);

			}
			usuario.addContent(listaDeCaronasDoUsuario);
			
			todosOsUsuarios.addContent(usuario);
			
			
		}
		

    
		Document arquivoUsuario = new Document(todosOsUsuarios);
		XMLOutputter xout = new XMLOutputter();

		try {
			FileWriter arquivo = new FileWriter(new File("Usuarios.xml"));
	    	xout.output(arquivoUsuario,arquivo);
			} catch (IOException e) {
			e.printStackTrace();
			}
		
		

		
		
	}
	
	public void lerXMLUsuarios(List<Usuario> listaDeUsuarios,List<Carona> listaDeCaronas) throws Exception{
		
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build("Usuarios.xml");
		Element elements = doc.getRootElement();
		
		List<Element> elementsUsuario = elements.getChildren();
		
		for (Element usuario : elementsUsuario) {
			Usuario usuario2 = new Usuario(usuario.getChildText("Login"), usuario.getChildText("Senha"), usuario.getChildText("Nome"), usuario.getChildText("Endereco"), usuario.getChildText("Email"));
			
			List<Element> elementsCarona = usuario.getChild("CaronasDoUsuario").getChildren();
		
			for (Element elementCarona : elementsCarona) {	
			
				
				Carona carona = new Carona(elementCarona.getChildText("Origem"), elementCarona.getChildText("Destino"), elementCarona.getChildText("Data"), elementCarona.getChildText("Hora"), Integer.parseInt(elementCarona.getChildText("Vagas")));
				
				List<Element> elementsSugestao = elementCarona.getChild("listaDeSugestoes").getChildren();
				
				for (Element elementSugestao : elementsSugestao) {
					
					Sugestao sugestao = new Sugestao(elementSugestao.getChildText("Pontos"), elementSugestao.getChildText("IdSessao"));
				    
					List<Element> elementsResposta = elementSugestao.getChild("RespostaDaSugestao").getChildren();
					for (Element elementResposta : elementsResposta) {
						
						Resposta resposta = new Resposta(elementResposta.getChildText("Ponto"));
						resposta.setIdResposta(elementResposta.getChildText("IdResposta"));
						
						sugestao.addResposta(resposta);
						
					}
					carona.addSugestao(sugestao);
				}
				
				List<Element> elementsPontosDeEncontro = elementCarona.getChildren("PontosDeEncontro");
				for (Element elementPontoDeEncontro : elementsPontosDeEncontro) {
					
					carona.addPontoDeEncontro(elementPontoDeEncontro.getChildText("PontoDeEncontro"));
				
				}
				
				List<Element> elementsSolicitacao = elementCarona.getChildren("Solicitacoes");
				
				for (Element elementSolicitacao : elementsSolicitacao) {
					
					Solicitacao solicitacao = new Solicitacao(elementSolicitacao.getChildText("IdSessao"), elementSolicitacao.getChildText("IdCarona"));
					carona.addSolicitacao(solicitacao);
				}
				
				List<Element> elementsSolicitacoesAceitas = elementCarona.getChildren("SolicitacoesAceitas");
				for (Element elementSolicitacaoAceita : elementsSolicitacoesAceitas) {
					
					Solicitacao solicitacao = new Solicitacao(elementSolicitacaoAceita.getChildText("IdSessao"), elementSolicitacaoAceita.getChildText("IdCarona"));
					carona.addSolicitacaoAceita(solicitacao);
				}
				
				
				usuario2.addCarona(carona);
				carona.setDonoDaCarona(usuario2);
			}
			
			
			listaDeUsuarios.add(usuario2);
			
		}
		

		for (Usuario usuario : listaDeUsuarios) {// insere as caronas na lista de caronas do sistema.
			for (Carona carona : usuario.getListaDeCaronasDoUsuario()) {
				listaDeCaronas.add(carona);
				
			}
			
		}
		
	
	}
	
	

}
