package com.dsi.capa.bean;

import java.io.Serializable;
import java.util.List;

import com.dsi.capa.db.dao.ClienteDAO;
import com.dsi.capa.db.model.Cliente;
import com.dsi.capa.db.util.DataSourceUtil;

public class ClienteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	
	private List<Cliente> clientes;

	private ClienteDAO clienteDAO;
	
	private Integer page;
	
	private Integer pageSize;
	
	public ClienteBean() {
		cliente = new Cliente();
		clienteDAO = new ClienteDAO(DataSourceUtil.getDataSource());
		page = new Integer(0);
		pageSize = new Integer(10);
		clientes = clienteDAO.findAll(page.intValue()*pageSize.intValue(), pageSize);
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String nextPage() {
		page = page.intValue() + 1;
		clientes = clienteDAO.findAll(page.intValue()*pageSize.intValue(), pageSize);
		return null;
	}
	
	public String prevPage() {
		page = page.intValue() -1 ;
		if(page < 0) {
			page = 0;
		}
		clientes = clienteDAO.findAll(page.intValue()*pageSize.intValue(), pageSize);
		return null;
	}
	
	public String save() {
		clienteDAO.save(cliente);
		cliente = new Cliente();
		return null;
	}
	
	public String delete() {
		clienteDAO.save(cliente);
		cliente = new Cliente();
		return null;
	}
	
	public String update() {
		clienteDAO.update(cliente);
		cliente = new Cliente();
		return null;
	}

}
