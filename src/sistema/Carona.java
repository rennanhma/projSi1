package sistema;

import java.util.GregorianCalendar;

/*
#US02 - Cadastro de caronas. Permitir o cadastro de caronas no perfil do usuário. 
#Deve-se informar o local de origem, o local de destino, data, hora de saída e quantidade de vagas disponíveis.
#Busca de caronas. Permitir que o usuário procure por caronas.
#Deve-se informar o local de origem e o local de destino.
#A busca só retorna caronas que ainda irá acontecer.
 */

public class Carona {
	private String origem, destino, data, hora;
	private int vagas;
	private GregorianCalendar dataAtual, horaAtual, dataHoraCarona;
	
	
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
	
	public static void main(String[] args) {
		Carona c = new Carona();
		GregorianCalendar a = new GregorianCalendar();
		System.out.println(a.getTime());
		System.out.println(c.dataHorarioValido("19/14/2011", "12:22"));
	}
}
