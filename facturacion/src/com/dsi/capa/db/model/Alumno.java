package com.dsi.capa.db.model;

import java.io.Serializable;

public class Alumno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String carnet;
	private Integer idusuario;
	private String nombreAlumno;
	private String apellidoAlumno;
	private String nitAlumno;
	private String duiAlumno;
	
	public Alumno() {
		super();
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno.toUpperCase();
	}

	public String getApellidoAlumno() {
		return apellidoAlumno;
	}

	public void setApellidoAlumno(String apellidoAlumno) {
		this.apellidoAlumno = apellidoAlumno.toUpperCase();
	}

	public String getNitAlumno() {
		return nitAlumno;
	}

	public void setNitAlumno(String nitAlumno) {
		this.nitAlumno = nitAlumno;
	}

	public String getDuiAlumno() {
		return duiAlumno;
	}

	public void setDuiAlumno(String duiAlumno) {
		this.duiAlumno = duiAlumno;
	}
	
	

}
