package testes;

import org.junit.*;

import sistema.Usuario;

public class TestaUsuario {
	Usuario usuario1;
	Usuario usuario2;

	@Test
	public void testLogin() throws Exception {
		try {
			usuario1 = new Usuario(null, "123456", "Mark Zuckerberg",
					"Palo Alto, California", "mark@facebook.com");
		} catch (Exception e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}
	}
}
