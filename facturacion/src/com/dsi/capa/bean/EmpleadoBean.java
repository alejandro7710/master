package com.dsi.capa.bean;

import java.io.Serializable;
import java.util.List;

import com.dsi.capa.db.dao.EmpleadoDAO;
import com.dsi.capa.db.model.Empleado;
import com.dsi.capa.db.util.DataSourceUtil;

public class EmpleadoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Empleado empleado;
	
	private List<Empleado> empleados;

	private EmpleadoDAO empleadoDAO;
	
	private Integer page;
	
	private Integer pageSize;
	
	private boolean busqueda;
	
	public EmpleadoBean() {
		empleado = new Empleado();
		//empleado.setCodigo(new Long(0));
		empleadoDAO = new EmpleadoDAO(DataSourceUtil.getDataSource());
		page = new Integer(0);
		pageSize = new Integer(10);
		empleados = buscarEmpleados();
	}
	
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public boolean isBusqueda() {
		return busqueda;
	}

	public void setBusqueda(boolean busqueda) {
		this.busqueda = busqueda;
	}

	public String nextPage() {
		page = page.intValue() + 1;
		empleados = buscarEmpleados();
		return null;
	}
	
	public String prevPage() {
		page = page.intValue() -1 ;
		if(page < 0) {
			page = 0;
		}
		empleados = buscarEmpleados();
		return null;
	}
	
	public String save() {
		empleadoDAO.save(empleado);
		empleado = new Empleado();
		//empleado.setCodigo(new Long(0));
		return null;
	}
	
	public String delete() {
		empleadoDAO.delete(empleado);
		empleado = new Empleado();
		//empleado.setCodigo(new Long(0));
		return null;
	}
	
	public String update() {
		empleadoDAO.update(empleado);
		empleado = new Empleado();
		//empleado.setCodigo(new Long(0));
		empleados = buscarEmpleados();
		return "empleadoLista";
	}
	
	public String find() {
		busqueda = true;
		empleados = buscarEmpleados();
		return "empleadoLista";
	}
	
	public String clean() {
		busqueda = false;
		empleado = new Empleado();
		return "empleadoLista";
	}
	
	public List<Empleado> buscarEmpleados() {
		List<Empleado> l = null;
		if(isBusqueda()) {
			l = empleadoDAO.findBy(empleado.getNombres(), empleado.getApellidos()
					, empleado.getSexo(), page.intValue()*pageSize.intValue()
					, pageSize);
		} else {
			l = empleadoDAO.findAll(page.intValue()*pageSize.intValue(), pageSize);
		}
		return l;
	}

}
