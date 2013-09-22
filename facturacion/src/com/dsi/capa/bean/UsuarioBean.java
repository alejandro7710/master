package com.dsi.capa.bean;



import java.io.Serializable;
import java.util.List;

import com.dsi.capa.db.dao.UsuarioDAO;
import com.dsi.capa.db.model.Usuario;
import com.dsi.capa.db.util.DataSourceUtil;

public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	private List<Usuario> Usuarios;

	private UsuarioDAO UsuarioDAO;
	
	private Integer page;
	
	private Integer pageSize;
	
	public UsuarioBean() {
		usuario = new Usuario();
	UsuarioDAO = new UsuarioDAO(DataSourceUtil.getDataSource());
//		page = new Integer(0);
//		pageSize = new Integer(10);
//		Usuarios = UsuarioDAO.findAll(page.intValue()*pageSize.intValue(), pageSize);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario Usuario) {
		this.usuario = Usuario;
	}

	public List<Usuario> getUsuarios() {
		return Usuarios;
	}

	public void setUsuarios(List<Usuario> Usuarios) {
		this.Usuarios = Usuarios;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String nextPage() {
		page = page.intValue() + 1;
		Usuarios = UsuarioDAO.findAll(page.intValue()*pageSize.intValue(), pageSize);
		return null;
	}
	
	public String prevPage() {
		page = page.intValue() -1 ;
		if(page < 0) {
			page = 0;
		}
		Usuarios = UsuarioDAO.findAll(page.intValue()*pageSize.intValue(), pageSize);
		return null;
	}
	
	public String save() {
		UsuarioDAO.save(usuario);
		usuario = new Usuario();
		return null;
	}
	
	public String delete() {
		UsuarioDAO.save(usuario);
		usuario = new Usuario();
		return null;
	}
	
	public String update() {
		UsuarioDAO.update(usuario);
		usuario = new Usuario();
		return null;
	}
	
	public String validarInicioSesion(){
		String result= UsuarioDAO.validarInicioSesion(usuario);
		return result;
		
		
	}

}
