package com.dsi.capa.db.model;

public class Usuario {
	String nombreUsuario;
	String contraUsuario;
	String emailUsuario;
	String codigoUsuario;
	
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContraUsuario() {
		return contraUsuario;
	}
	public void setContraUsuario(String contraUsuario) {
		this.contraUsuario = contraUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
}
