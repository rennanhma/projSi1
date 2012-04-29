package sistemasdecaronas.projSi1.easy;

import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class TesteFachada {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> files = new ArrayList<String>();
		// Put the us1.txt file into the "test scripts" list
		files.add("testes/US01.txt");
		files.add("testes/US02.txt");
		files.add("testes/US03.txt");
	    files.add("testes/US04.txt");
	    files.add("testes/US05.txt");
	    files.add("testes/US06.txt");
		files.add("testes/US07.txt");
		files.add("testes/US08.txt");

		Fachada siTeste = new Fachada();
		// Instantiate EasyAccept façade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(siTeste, files);

		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
	}

}
