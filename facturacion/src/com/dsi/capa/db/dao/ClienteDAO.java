package com.dsi.capa.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.dsi.capa.db.model.Cliente;

public class ClienteDAO extends BasicDAO<Cliente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public ClienteDAO() {
		super();
	}

	public ClienteDAO(DataSource ds) {
		super(ds);
	}

	@Override
	public void save(Cliente target) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO CLIENTE(NOMBRES_CLIENTE, APELLIDOS_CLIENTE,");
		sql.append("SEXO_CLIENTE) VALUES(?, ?, ?)");
		try {
			preparedStatement = getConnection().prepareStatement(
					sql.toString());
			preparedStatement.setString(1, target.getNombres());
			preparedStatement.setString(2, target.getApellidos());
			preparedStatement.setString(3, target.getSexo());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public void update(Cliente target) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE CLIENTE SET NOMBRES_CLIENTE = ?, APELLIDOS_CLIENTE = ?");
		sql.append("SEXO_CLIENTE = ? WHERE CODIGO_CLIENTE = ? ");
		try {
			preparedStatement = getConnection().prepareStatement(
					sql.toString());
			preparedStatement.setString(1, target.getNombres());
			preparedStatement.setString(2, target.getApellidos());
			preparedStatement.setString(3, target.getSexo());
			preparedStatement.setLong(4, target.getCodigo());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public void delete(Cliente target) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM CLIENTE WHERE CODIGO_CLIENTE = ? ");
		try {
			preparedStatement = getConnection().prepareStatement(
					sql.toString());
			preparedStatement.setLong(1, target.getCodigo());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public List<Cliente> findAll() {
		return findAll(null, null);
	}

	@Override
	public List<Cliente> findAll(Integer firstRow, Integer maxRows) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		int i = 0;
		int lastRow = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NOMBRES_CLIENTE, APELLIDOS_CLIENTE,");
		sql.append("SEXO_CLIENTE, CODIGO_CLIENTE FROM ORA11DB.CLIENTE");
		
		try {
			preparedStatement = getConnection().prepareStatement(sql.toString());
			resultSet = preparedStatement.executeQuery();
			if(firstRow != null && maxRows != null) {
				lastRow = firstRow.intValue() + maxRows;
				while(resultSet.next()) {
					if(i >= firstRow.intValue()) {
						Cliente c = new Cliente();
						c.setApellidos(resultSet.getString("APELLIDOS_CLIENTE"));
						c.setNombres(resultSet.getString("NOMBRES_CLIENTE"));
						c.setSexo(resultSet.getString("SEXO_CLIENTE"));
						c.setCodigo(resultSet.getLong("CODIGO_CLIENTE"));
						clientes.add(c);
					}
					i++;
					if(i >= lastRow) {
						break;
					}
				}
			} else {
				while(resultSet.next()) {
					Cliente c = new Cliente();
					c.setApellidos(resultSet.getString("APELLIDOS_CLIENTE"));
					c.setNombres(resultSet.getString("NOMBRES_CLIENTE"));
					c.setSexo(resultSet.getString("SEXO_CLIENTE"));
					c.setCodigo(resultSet.getLong("CODIGO_CLIENTE"));
					clientes.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return clientes;
	}

}
