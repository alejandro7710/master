package com.dsi.capa.db.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import com.dsi.capa.db.model.Alumno;


public class AlumnoDAO extends BasicDAO<Alumno> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public AlumnoDAO() {
		super();
	}

	public AlumnoDAO(DataSource ds) {
		super(ds);
	}
	//insertar en la base
	@Override
	public void save(Alumno target) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO Alumno(CARNET, NOMBREALUMNO, APELLIDOALUMNO, NITALUMNO, ");
		sql.append("DUIALUMNO) VALUES(?, ?, ?, ?, ?)");
		try {
			preparedStatement = getConnection().prepareStatement(
					sql.toString());
			preparedStatement.setString(1, target.getCarnet());
			preparedStatement.setString(2, target.getNombreAlumno());
			preparedStatement.setString(3, target.getApellidoAlumno());
			preparedStatement.setString(4, target.getNitAlumno());
			preparedStatement.setString(5, target.getDuiAlumno());
//			preparedStatement.setString(1, target.getNombres());
//			preparedStatement.setString(2, target.getApellidos());
//			preparedStatement.setString(3, target.getSexo());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public void update(Alumno target) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE Alumno SET NOMBREALUMNO = ?, APELLIDOALUMNO = ?, NITALUMNO= ?, ");
		sql.append("DUIALUMNO = ? WHERE CARNET = ? ");
		try {
			preparedStatement = getConnection().prepareStatement(
					sql.toString());
			
			preparedStatement.setString(1, target.getNombreAlumno());
			preparedStatement.setString(2, target.getApellidoAlumno());
			preparedStatement.setString(3, target.getNitAlumno());
			preparedStatement.setString(4, target.getDuiAlumno());
			preparedStatement.setString(5, target.getCarnet());
//			preparedStatement.setString(1, target.getNombres());
//			preparedStatement.setString(2, target.getApellidos());
//			preparedStatement.setString(3, target.getSexo());
//			preparedStatement.setLong(4, target.getCodigo());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public void delete(Alumno target) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM Alumno WHERE CARNET = ? ");
		try {
			preparedStatement = getConnection().prepareStatement(
					sql.toString());
			preparedStatement.setString(1, target.getCarnet());
//			preparedStatement.setLong(1, target.getCodigo());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public List<Alumno> findAll() {
		return findAll(null, null);
	}

	@Override
	public List<Alumno> findAll(Integer firstRow, Integer maxRows) {
		List<Alumno> Alumno = new ArrayList<Alumno>();
		int i = 0;
		int lastRow = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT CARNET, NOMBREALUMNO, APELLIDOALUMNO, ");
		sql.append("NITALUMNO, DUIALUMNO FROM Alumno");
		
		try {
			preparedStatement = getConnection().prepareStatement(sql.toString());
			resultSet = preparedStatement.executeQuery();
			if(firstRow != null && maxRows != null) {
				lastRow = firstRow.intValue() + maxRows;
				while(resultSet.next()) {
					if(i >= firstRow.intValue()) {
						Alumno c = new Alumno();
						c.setCarnet(resultSet.getString("CARNET"));
						c.setNombreAlumno(resultSet.getString("NOMBREALUMNO"));
						c.setApellidoAlumno(resultSet.getString("APELLIDOALUMNO"));
						c.setNitAlumno(resultSet.getString("NITALUMNO"));
						c.setDuiAlumno(resultSet.getString("DUIALUMNO"));
//						c.setApellidos(resultSet.getString("APELLIDOS_Alumno"));
//						c.setNombres(resultSet.getString("NOMBRES_Alumno"));
//						c.setSexo(resultSet.getString("SEXO_Alumno"));
//						c.setCodigo(resultSet.getLong("CODIGO_Alumno"));
						Alumno.add(c);
					}
					i++;
					if(i >= lastRow) {
						break;
					}
				}
			} else {
				while(resultSet.next()) {
					Alumno c = new Alumno();
					c.setCarnet(resultSet.getString("CARNET"));
					c.setNombreAlumno(resultSet.getString("NOMBREALUMNO"));
					c.setApellidoAlumno(resultSet.getString("APELLIDOALUMNO"));
					c.setNitAlumno(resultSet.getString("NITALUMNO"));
					c.setDuiAlumno(resultSet.getString("DUIALUMNO"));
//					c.setApellidos(resultSet.getString("APELLIDOS_Alumno"));
//					c.setNombres(resultSet.getString("NOMBRES_Alumno"));
//					c.setSexo(resultSet.getString("SEXO_Alumno"));
//					c.setCodigo(resultSet.getLong("CODIGO_Alumno"));
					Alumno.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return Alumno;
	}
	
	/************************* METODO QUE BUSCA UN SOLO ALUMNO *********************************/
	public Alumno buscarAlumno(Alumno target)
	{
		Alumno c = new Alumno();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT CARNET, NOMBREALUMNO, APELLIDOALUMNO, ");
		sql.append("NITALUMNO, DUIALUMNO FROM Alumno WHERE CARNET = ?");
		
		try {
			preparedStatement = getConnection().prepareStatement(sql.toString());
			preparedStatement.setString(1, target.getCarnet());
			resultSet = preparedStatement.executeQuery();
			
				while(resultSet.next()) {
						c.setCarnet(resultSet.getString("CARNET"));
						c.setNombreAlumno(resultSet.getString("NOMBREALUMNO"));
						c.setApellidoAlumno(resultSet.getString("APELLIDOALUMNO"));
						c.setNitAlumno(resultSet.getString("NITALUMNO"));
						c.setDuiAlumno(resultSet.getString("DUIALUMNO"));
					}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return c;

	}//FIN METODO buscarAlumno
	
	/************************* METODO QUE BUSCA UN SOLO ALUMNO *********************************/
	public String correlativo(Alumno target)
	{
		//Alumno c = new Alumno();
		String c=null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT CARNET FROM Alumno WHERE CARNET LIKE ?");
		
		try {
			preparedStatement = getConnection().prepareStatement(sql.toString());
			preparedStatement.setString(1, target.getCarnet() + "%");
			resultSet = preparedStatement.executeQuery();
			
				if(resultSet.last()) {
						c = resultSet.getString("CARNET");
						c = c.substring(4);//OBTENER ULTIMO CORRELATIVO
						//INCREMENTAR CORRELATIVO
						c = String.format("%03d", (Integer.parseInt(c))+1);		
					}				
				//return c;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return c;

	}//FIN METODO buscarAlumno

}
