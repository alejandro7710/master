package com.dsi.capa.db.dao;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.dsi.capa.db.model.Usuario;

public class UsuarioDAO extends BasicDAO<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public UsuarioDAO() {
		super();
	}

	public UsuarioDAO(DataSource ds) {
		super(ds);
	}

	@Override
	public void save(Usuario target) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO Usuario(NOMBRES_Usuario, APELLIDOS_Usuario,");
		sql.append("SEXO_Usuario) VALUES(?, ?, ?)");
		try {
			preparedStatement = getConnection().prepareStatement(
					sql.toString());
			preparedStatement.setString(1, target.getNombreUsuario());
			preparedStatement.setString(2, target.getContraUsuario());
			preparedStatement.setString(3, target.getEmailUsuario());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public void update(Usuario target) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE Usuario SET NOMBRES_Usuario = ?, APELLIDOS_Usuario = ?");
		sql.append("SEXO_Usuario = ? WHERE CODIGO_Usuario = ? ");
		try {
			preparedStatement = getConnection().prepareStatement(
					sql.toString());
			preparedStatement.setString(1, target.getNombreUsuario());
			preparedStatement.setString(2, target.getContraUsuario());
			preparedStatement.setString(3, target.getEmailUsuario());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public void delete(Usuario target) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM Usuario WHERE CODIGO_Usuario = ? ");
		try {
			preparedStatement = getConnection().prepareStatement(
					sql.toString());
			preparedStatement.setString(1, target.getCodigoUsuario());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public List<Usuario> findAll() {
		return findAll(null, null);
	}

	@Override
	public List<Usuario> findAll(Integer firstRow, Integer maxRows) {
		List<Usuario> Usuarios = new ArrayList<Usuario>();
		int i = 0;
		int lastRow = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select email from usuario ");
		
		
		try {
			preparedStatement = getConnection().prepareStatement(sql.toString());
			resultSet = preparedStatement.executeQuery();
			if(firstRow != null && maxRows != null) {
				lastRow = firstRow.intValue() + maxRows;
				while(resultSet.next()) {
					if(i >= firstRow.intValue()) {
						Usuario c = new Usuario();
						
						c.setNombreUsuario(resultSet.getString("NOMBREUSUARIO"));
						c.setEmailUsuario(resultSet.getString("EMAIL"));
						
						Usuarios.add(c);
					}
					i++;
					if(i >= lastRow) {
						break;
					}
				}
			} else {
				while(resultSet.next()) {
					Usuario c = new Usuario();
					c.setNombreUsuario(resultSet.getString("NOMBREUSUARIO"));
					c.setEmailUsuario(resultSet.getString("EMAIL"));
					
					Usuarios.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return Usuarios;
	}

	public String validarInicioSesion(com.dsi.capa.db.model.Usuario target) {
		
		String aux="fallo";
		int lastRow = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from usuario where NOMBREUSUARIO=? and CONTRASENIA=? ");
		
		
		try {
			preparedStatement = getConnection().prepareStatement(sql.toString());
			preparedStatement.setString(1, target.getNombreUsuario());
			preparedStatement.setString(2, target.getContraUsuario());
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.getRow()!=0)
				aux="ok";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		
		return aux;
	}

}
