package sistema;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/*
#US02 - Cadastro de caronas. Permitir o cadastro de caronas no perfil do usuário. 
#Deve-se informar o local de origem, o local de destino, data, hora de saída e quantidade de vagas disponíveis.
#Busca de caronas. Permitir que o usuário procure por caronas.
#Deve-se informar o local de origem e o local de destino.
#A busca só retorna caronas que ainda irá acontecer.
 */


public class Carona {
	
	private Usuario donoDaCarona;
	public  List<Solicitacao> listaDeSolicitacaoAceitas = new ArrayList<Solicitacao>();
	public List<Sugestao> listaDeSugestoes = new ArrayList<Sugestao>();
	public  List<Solicitacao> listaDeSolicitacao = new ArrayList<Solicitacao>();
	private String origem, destino, data, hora, idDaCarona;
	private int vagas;
	public List<String> pontoDeEncontro = new ArrayList<String>();
	
	
	
	public Carona(String origem, String destino, String data, String hora, int vagas){
		   this.origem = origem;
		   this.destino = destino;
		   this.data = data;
		   this.hora = hora;
		   this.vagas = vagas;
		   idDaCarona = UUID.randomUUID().toString();	
	}
	
	
	public List<Solicitacao> getListaDeSolicitacao() {
		return listaDeSolicitacao;
	}


	public List<String> getPontoDeEncontro() {
		return pontoDeEncontro;
	}


	public List<Solicitacao> getListaDeSolicitacaoAceitas() {
		return listaDeSolicitacaoAceitas;
	}


	public void setPontoDeEncontro(List<String> pontoDeEncontro) {
		this.pontoDeEncontro = pontoDeEncontro;
	}


	public String getIdDaCarona() {
		return idDaCarona;
	}


	public List<Sugestao> getSugestoes() {
		return listaDeSugestoes;
	}


	public void addSugestao(Sugestao sugestao) {
		listaDeSugestoes.add(sugestao);
	}


	public void setIdDaCarona(String idDaCarona) {
		this.idDaCarona = idDaCarona;
	}

	
	public Usuario getDonoDaCarona() {
		return donoDaCarona;
	}


	public void setDonoDaCarona(Usuario donoDaCarona) {
		this.donoDaCarona = donoDaCarona;
	}

	
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getVagas() {
		return vagas;
	}
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	

	public void addSolicitacao(Solicitacao solicitacao) {
		this.listaDeSolicitacao.add(solicitacao);
		
	}

	public void addSolicitacaoAceita(Solicitacao solicitacao) {
		this.listaDeSolicitacaoAceitas.add(solicitacao);
		
	}

	public void addPontoDeEncontro(String pontos) {
		this.pontoDeEncontro.add(pontos);
		
	}


	public void removeSugestao(Sugestao sugestao) {
		  listaDeSugestoes.remove(sugestao);
		
		
	}
	
	public void removeSolicitacao(Solicitacao solicitacao)
	{
		listaDeSolicitacao.remove(solicitacao);
	}
	
	public void removeSolicitacaoAceita(Solicitacao solicitacao)
	{
		listaDeSolicitacaoAceitas.remove(solicitacao);
	}
	
	public void removePontoDeEncontro(String ponto)
	{
		pontoDeEncontro.remove(ponto);
	}


}
