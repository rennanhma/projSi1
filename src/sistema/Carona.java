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
	public List<Sugestao> listaDeSugestoes = new ArrayList<Sugestao>();
	public  List<Solicitacao> listaDeSolicitacao = new ArrayList<Solicitacao>();
	private String origem, destino, data, hora, idDaCarona;
	private int vagas;
	private GregorianCalendar dataAtual, dataHoraCarona;
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


	public boolean dataHorarioValido(String data, String hora)
	{
		dataAtual = new GregorianCalendar();
		return dataAtual.before(dataGregorianCalendar(data, hora));
	}
	
	public Usuario getDonoDaCarona() {
		return donoDaCarona;
	}


	public void setDonoDaCarona(Usuario donoDaCarona) {
		this.donoDaCarona = donoDaCarona;
	}

	
	
	
	/**
	 * 
	 * @param data data da carona : dd/mm/aaaa
	 * @param hora horario da carona: hh:mm
	 * @return gregorian calendar do horario da carona
	 */
	public GregorianCalendar dataGregorianCalendar(String data, String hora)
	{
		
		String[] dataDividida = data.split("/");
		String[] horaDividida = hora.split(":");
		int dia = Integer.parseInt(dataDividida[0]);
		int mes = Integer.parseInt(dataDividida[1]);
		int ano = Integer.parseInt(dataDividida[2]);
		int horas = Integer.parseInt(horaDividida[0]);
		int minutos = Integer.parseInt(horaDividida[1]);
		dataHoraCarona = new GregorianCalendar(ano, mes, dia, horas, minutos);
		return dataHoraCarona;
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
	
	public static boolean isDataValida(String dataRecebida) {
		
		boolean dataBoolean = true;
		Calendar calendar = new GregorianCalendar();
		Calendar calendar2 = new GregorianCalendar();
	    //calendar2.set(calendar2.YEAR, calendar2.MONTH, calendar2.DATE);
		String[] arrayDataRecebida = dataRecebida.split("/");
		
		Pattern pt = Pattern.compile("[0-9]{2}[/][0-9]{2}[/][0-9]{4}");
		Matcher mt = pt.matcher(dataRecebida);
		dataBoolean = mt.matches();

		
		if (dataBoolean) {
			
			int ano = Integer.parseInt(arrayDataRecebida[2]);
			int mes = Integer.parseInt(arrayDataRecebida[1])-1;
			int dia = Integer.parseInt(arrayDataRecebida[0]);
			
			calendar.set(ano,mes,dia);
			
           if (calendar.before(calendar2)) {
				dataBoolean = false;
			}
				
			if (calendar.get(calendar.DATE) != dia || calendar.get(calendar.MONTH) != mes || calendar.get(calendar.YEAR) != ano) {
					
				dataBoolean = false;
			}
		}	
		
		return dataBoolean;
	}

	
		
		public static boolean horaValida(String hora){
			
			String[] arrayHoraRecebida = hora.split(":");
			Pattern pt = Pattern.compile("[0-9]{2}[:][0-9]{2}");
		    Matcher m = pt.matcher(hora);
			boolean formatoCorreto = m.matches();
			boolean horaValida = false;
			
			if (formatoCorreto) {
				int campoHora = Integer.parseInt(arrayHoraRecebida[0]);
				int campoMinutos = Integer.parseInt(arrayHoraRecebida[1]);
				
				if (campoHora >=0 && campoHora <= 23 && campoMinutos >= 0 && campoMinutos <= 59) {
					
					horaValida = true;
								
				}
			}
				

		
		return horaValida;
	}
	
	
	public static boolean ehBissexto(int ano) {
		Calendar calendarC = new GregorianCalendar();

		if (((GregorianCalendar) calendarC).isLeapYear(ano)) {

			return true;

		}
		return false;
	}
	

	public void addSolicitacao(Solicitacao solicitacao) {
		this.listaDeSolicitacao.add(solicitacao);
		
	}


	public void addPontoDeEncontro(String pontos) {
		this.pontoDeEncontro.add(pontos);
		
	}


	public void removeSugestao(Sugestao sugestao) {
		  listaDeSugestoes.remove(sugestao);
		
		
	}


}
