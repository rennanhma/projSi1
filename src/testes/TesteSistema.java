package testes;

import static org.junit.Assert.*;

import org.junit.*;

import sistema.SistemaDeCarona;

public class TesteSistema {

	SistemaDeCarona sistema;

	@Before
	public void antes() throws Exception {
		sistema = new SistemaDeCarona();
		sistema.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		sistema.criarUsuario("steve", "5t3v3", "Steven Paul Jobs",
				"Palo Alto, California", "jobs@apple.com");
		sistema.criarUsuario("bill", "severino", "William Henry Gates III",
				"Medina, Washington", "bil@apple.com");
		try {
			sistema.criarUsuario("", "sda", "fasdf", "Pfa", "xcv@apple.com");
		} catch (Exception e) {
			assertEquals("Login inválido", e.getMessage());

		}
		try {
			sistema.criarUsuario(null, "fas", "afa", "Palo dsia",
					"asd@apple.com");
		} catch (Exception e) {
			assertEquals("Login inválido", e.getMessage());
		}
	}

	@After
	public void depois() throws Exception {
	}

	@Test
	public void testeLista() throws Exception {
		// adicionou os 3 mark , steave e bill
		assertEquals(3, sistema.getUsuarios().size());

	}

	@Test
	public void testeAbrirSessao() throws Exception {
		try {
			sistema.abrirSessao("mark", "m@rk");
		} catch (Exception e) {
			assertEquals("Login inválido ou Senha", e.getMessage());
		}
	}

}
