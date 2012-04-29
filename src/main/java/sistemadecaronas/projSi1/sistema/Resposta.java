package sistemadecaronas.projSi1.sistema;

import java.util.UUID;

public class Resposta {
	
	public String idResposta,pontos;
	
	public Resposta(String pontos){
		this.pontos = pontos;
		idResposta = UUID.randomUUID().toString();	
	}

	public String getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(String idResposta) {
		this.idResposta = idResposta;
	}

	public String getPontos() {
		return pontos;
	}

	public void setPontos(String pontos) {
		this.pontos = pontos;
	}
	
	
	
	

}
