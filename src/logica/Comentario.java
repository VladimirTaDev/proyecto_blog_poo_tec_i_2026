package logica;

import java.time.LocalDateTime;

public class Comentario {
	private LocalDateTime fechaCreacion;
	private String emailAutor;
	private String direccionIP;
	private String texto;

	public Comentario(String email, String ip, String texto) {
		this.emailAutor = email;
		this.direccionIP = ip;
		this.texto = texto;
		fechaCreacion = LocalDateTime.now();
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public String getEmail() {
		return emailAutor;
	}

	public String getIp() {
		return direccionIP;
	}

	public String getTexto() {
		return texto;
	}

	public String toString() {
		String resultado = emailAutor + " - " + direccionIP + " - " + fechaCreacion.toString() + "\n";
		resultado += texto + "\n";
		return resultado;
	}
}