package com.dsi.capa.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.dsi.capa.db.model.Empleado;

public class EmpleadoDAO extends BasicDAO<Empleado>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpleadoDAO() {
		super();
	}

	public EmpleadoDAO(DataSource ds) {
		super(ds);
	}
	
	@Override
	public void save(Empleado target) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ORA11DB.EMPLEADO (NOMBRES_EMPLEADO, APELLIDOS_EMPLEADO,");
		sql.append("SEXO_EMPLEADO) VALUES(?, ?, ?)");
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
	public void update(Empleado target) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE EMPLEADO SET NOMBRES_EMPLEADO = ?, APELLIDOS_EMPLEADO = ?");
		sql.append(", SEXO_EMPLEADO = ? WHERE CODIGO_EMPLEADO = ? ");
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
	public void delete(Empleado target) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM EMPLEADO WHERE CODIGO_EMPLEADO = ? ");
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
	public List<Empleado> findAll() {
		return findAll(null, null);
	}

	@Override
	public List<Empleado> findAll(Integer firstRow, Integer maxRows) {
		List<Empleado> clientes = new ArrayList<Empleado>();
		int i = 0;
		int lastRow = 0;
		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT NOMBRES_EMPLEADO, APELLIDOS_EMPLEADO,"); para oracle
//		sql.append("SEXO_EMPLEADO, CODIGO_EMPLEADO FROM ORA11DB.EMPLEADO");para oracle
		
		sql.append("SELECT NOMBRES_EMPLEADO, APELLIDOS_EMPLEADO,");
		sql.append("SEXO_EMPLEADO, CODIGO_EMPLEADO FROM EMPLEADO");
		try {
			preparedStatement = getConnection().prepareStatement(sql.toString());
			resultSet = preparedStatement.executeQuery();
			if(firstRow != null && maxRows != null) {
				lastRow = firstRow.intValue() + maxRows;
				while(resultSet.next()) {
					if(i >= firstRow.intValue()) {
						Empleado c = new Empleado();
						c.setApellidos(resultSet.getString("APELLIDOS_EMPLEADO"));
						c.setNombres(resultSet.getString("NOMBRES_EMPLEADO"));
						c.setSexo(resultSet.getString("SEXO_EMPLEADO"));
						c.setCodigo(resultSet.getLong("CODIGO_EMPLEADO"));
						clientes.add(c);
					}
					i++;
					if(i >= lastRow) {
						break;
					}
				}
			} else {
				while(resultSet.next()) {
					Empleado c = new Empleado();
					c.setApellidos(resultSet.getString("APELLIDOS_EMPLEADO"));
					c.setNombres(resultSet.getString("NOMBRES_EMPLEADO"));
					c.setSexo(resultSet.getString("SEXO_EMPLEADO"));
					c.setCodigo(resultSet.getLong("CODIGO_EMPLEADO"));
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
	
	public List<Empleado> findBy(String nombres, String apellidos, String sexo) {
		return findBy(nombres, apellidos, sexo, null, null);
	}
	
	public List<Empleado> findBy(String nombres, String apellidos, String sexo
			, Integer firstRow, Integer maxRows) {
		List<Empleado> clientes = new ArrayList<Empleado>();
		int pos = 1;
		int i = 0;
		int lastRow = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NOMBRES_EMPLEADO, APELLIDOS_EMPLEADO,");
		sql.append("SEXO_EMPLEADO, CODIGO_EMPLEADO FROM EMPLEADO WHERE 1 = 1");
		
		if(!isStringEmpty(nombres)) {
			sql.append(" AND UPPER(NOMBRES_EMPLEADO) LIKE ?");
		}
		if(!isStringEmpty(apellidos)) {
			sql.append(" AND UPPER(APELLIDOS_EMPLEADO) LIKE ?");
		}
		if(!isStringEmpty(sexo)) {
			sql.append(" AND SEXO_EMPLEADO = ?");
		}
		
		try {
			preparedStatement = getConnection().prepareStatement(sql.toString());
			if(!isStringEmpty(nombres)) {
				preparedStatement.setString(pos, "%"+nombres.toUpperCase()+"%");
				pos++;
			}
			if(!isStringEmpty(apellidos)) {
				preparedStatement.setString(pos, "%"+apellidos.toUpperCase()+"%");
				pos++;
			}
			if(!isStringEmpty(sexo)) {
				preparedStatement.setString(pos, sexo);
				pos++;
			}
			resultSet = preparedStatement.executeQuery();
			if(firstRow != null && maxRows != null) {
				lastRow = firstRow.intValue() + maxRows;
				while(resultSet.next()) {
					if(i >= firstRow.intValue()) {
						Empleado c = new Empleado();
						c.setApellidos(resultSet.getString("APELLIDOS_EMPLEADO"));
						c.setNombres(resultSet.getString("NOMBRES_EMPLEADO"));
						c.setSexo(resultSet.getString("SEXO_EMPLEADO"));
						c.setCodigo(resultSet.getLong("CODIGO_EMPLEADO"));
						clientes.add(c);
					}
					i++;
					if(i >= lastRow) {
						break;
					}
				}
			} else {
				while(resultSet.next()) {
					Empleado c = new Empleado();
					c.setApellidos(resultSet.getString("APELLIDOS_EMPLEADO"));
					c.setNombres(resultSet.getString("NOMBRES_EMPLEADO"));
					c.setSexo(resultSet.getString("SEXO_EMPLEADO"));
					c.setCodigo(resultSet.getLong("CODIGO_EMPLEADO"));
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
