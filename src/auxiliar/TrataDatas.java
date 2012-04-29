package auxiliar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrataDatas {
	
	private GregorianCalendar dataAtual, dataHoraCarona;
	
	public boolean dataHorarioValido(String data, String hora)
	{
		dataAtual = new GregorianCalendar();
		return dataAtual.before(dataGregorianCalendar(data, hora));
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




}
