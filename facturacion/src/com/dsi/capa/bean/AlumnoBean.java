package com.dsi.capa.bean;


import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.dsi.capa.db.dao.AlumnoDAO;
import com.dsi.capa.db.model.Alumno;
import com.dsi.capa.db.util.DataSourceUtil;

public class AlumnoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Alumno alumno;
	
	private List<Alumno> alumnos;

	private AlumnoDAO alumnoDAO;
	
	private Integer page;
	
	private Integer pageSize;
	
	public AlumnoBean() {
		alumno = new Alumno();
		alumnoDAO = new AlumnoDAO(DataSourceUtil.getDataSource());
//		page = new Integer(0);
//		pageSize = new Integer(10);
//		Alumnos = AlumnoDAO.findAll(page.intValue()*pageSize.intValue(), pageSize);
		
	}
	
	public String validarAlumno()
	{
		String exito="alumnoExitoso";
		String fracaso="ingresoAlumno";
		Calendar fecha = new GregorianCalendar();


		//VALIDAR CAMPOS VACIOS
		if(alumno.getNombreAlumno()=="" || alumno.getApellidoAlumno()=="" 
				|| alumno.getNitAlumno()=="" || alumno.getDuiAlumno()=="")
		{
			return fracaso;
		}
		//VALIDAR PATRON DEL NIT
		if(!Pattern.matches("\\d\\d\\d\\d-\\d\\d\\d\\d\\d\\d-\\d\\d\\d-\\d", alumno.getNitAlumno()))
		{
			return fracaso;
		}
		//VALIDAR PATRON DEL DUI
		if(!Pattern.matches("\\d\\d\\d\\d\\d\\d\\d\\d-\\d", alumno.getDuiAlumno()))
		{
			return fracaso;
		}
		
		//GENERAR EL CARNET DEL ALUMNO
		String a="",b="",anio="", correlativo="";
		int posicion=0;
		
		posicion = alumno.getApellidoAlumno().indexOf(' ');//OBTENER LA POSICION DEL PRIMER ESPACIO
		a += alumno.getApellidoAlumno().charAt(0);//ALMACENAR PRIMER LETRA DE LA CADENA
		b += alumno.getApellidoAlumno().charAt(posicion+1);//ALMACENAR SEGUNDA LETRA
		anio = Integer.toString(fecha.get(Calendar.YEAR));
		alumno.setCarnet(a+b+anio.substring(2));
		
		//LLAMAR AL METODO DE OBTENCION DE CORRELATIVO
		correlativo = alumnoDAO.correlativo(alumno);
		//JOptionPane.showMessageDialog(null, correlativo);
		if(correlativo == null)
		{
			alumno.setCarnet(a+b+anio.substring(2)+"001");
		}
		else
		{
			alumno.setCarnet(a+b+anio.substring(2)+correlativo);
		}		
		
		//REALIZAR LA INSERCION DEL ALUMNO
		alumnoDAO.save(alumno);
		//JOptionPane.showMessageDialog(null, alumno.toString());
		
		return exito;
	}//FIN METODO validarAlumno
	
	//METODO PARA CARGAR UN SOLO ALUMNO
	public String cargarAlumno()
	{
		alumno = alumnoDAO.buscarAlumno(alumno);
		
		return "actualizaAlumno";
	}
	//METODO PARA CARGAR UN SOLO ALUMNO
	public String cargarAlumnoElimina()
	{
		alumno = alumnoDAO.buscarAlumno(alumno);
		
		return "eliminaAlumno";
	}
	//METODO PARA ACTUALIZAR AL ALUMNO
	public String actualizarAlumno()
	{
				//VALIDAR CAMPOS VACIOS
				if(alumno.getNombreAlumno()=="" || alumno.getApellidoAlumno()=="" || alumno.getCarnet()==""
						|| alumno.getNitAlumno()=="" || alumno.getDuiAlumno()=="")
				{
					return "actualizaAlumno";
				}
				//VALIDAR PATRON DEL CARNET
				/*if(!Pattern.matches("\\[A-Za-z]\\[A-Za-z]\\d\\d\\d\\d\\d", alumno.getCarnet()))
				{
					return "actualizaAlumno";
				}*/
				//VALIDAR PATRON DEL NIT
				if(!Pattern.matches("\\d\\d\\d\\d-\\d\\d\\d\\d\\d\\d-\\d\\d\\d-\\d", alumno.getNitAlumno()))
				{
					return "actualizaAlumno";
				}
				//VALIDAR PATRON DEL DUI
				if(!Pattern.matches("\\d\\d\\d\\d\\d\\d\\d\\d-\\d", alumno.getDuiAlumno()))
				{
					return "actualizaAlumno";
				}
		
		update();
		
		return "actualizaAlumno";
	}
	//METODO PARA ELIMINAR AL ALUMNO
	public String eliminarAlumno()
	{
		delete();
		
		return "eliminaAlumno";
	}

	//METODO PARA EL BOTON VOLVER PARA alumnoExitoso.xhtml, CREARA UN NUEVO BEAN
	public String volver()
	{
		alumno = new Alumno();
		
		return "ingresoAlumno";
	}
	
	/**************************************************************************/
	

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String nextPage() {
		page = page.intValue() + 1;
		alumnos = alumnoDAO.findAll(page.intValue()*pageSize.intValue(), pageSize);
		return null;
	}
	
	public String prevPage() {
		page = page.intValue() -1 ;
		if(page < 0) {
			page = 0;
		}
		alumnos = alumnoDAO.findAll(page.intValue()*pageSize.intValue(), pageSize);
		return null;
	}
	
	public String save() {
		alumnoDAO.save(alumno);
		alumno = new Alumno();
		return null;
	}
	
	public String delete() {
		alumnoDAO.delete(alumno);
		alumno = new Alumno();
		return null;
	}
	
	public String update() {
		alumnoDAO.update(alumno);
		alumno = new Alumno();
		return null;
	}

}
