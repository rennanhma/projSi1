package persistencia;


import java.util.LinkedList;
import java.util.List;



public class InterfaceXML<E> {
   
	private String nomeDoArquivo = "noName";
	private List<E> listaASalvar;
	private Serializador<List<E>> serializador; 
	
	public InterfaceXML(String nomeDoArquivo,List<E> listaASalvar){
		this.nomeDoArquivo = nomeDoArquivo;
		this.listaASalvar = listaASalvar;
		serializador = new Serializador<List<E>>();
	}
	

	/**
	* carrega os dados do arquivo pra lista de perfis
	*/

	public void loadData() {
	serializador = new Serializador<List<E>>();
	listaASalvar = serializador.recuperar(nomeDoArquivo);
	if (listaASalvar == null)
	listaASalvar = new LinkedList<E>();
	}
    


	public void saveData() {
		serializador.salvar(nomeDoArquivo, listaASalvar);
		
	}




}
