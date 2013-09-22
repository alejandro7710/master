package com.dsi.capa.db.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

import oracle.jdbc.pool.OracleDataSource;


public abstract class BasicDAO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected DataSource ds;
	
	protected Connection connection;
	
	protected PreparedStatement preparedStatement;
	
	protected ResultSet resultSet;

	public BasicDAO() {}
	
	public BasicDAO(DataSource ds) {
		super();
		this.ds = null;
	}
	
	public DataSource getDataSource() {
		if(ds == null) {
			//Es por ejemplo nada mas
			OracleDataSource ods;
			try {
				ods = new OracleDataSource();
				ods.setDriverType("thin");
				ods.setServerName("localhost");
				ods.setNetworkProtocol("tcp");
				ods.setDatabaseName("xe");
				ods.setPortNumber(1521);
				ods.setUser("system");
				ods.setPassword("system$1");
				ds = ods;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ds;		
	}
	
	public void closeConnection() {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				resultSet = null;
			}
		}
		
		if(preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				preparedStatement = null;
			}
		} 
		
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				connection = null;
			}
		}
	}

	public Connection getConnection() {
		/*if(connection == null) {
			try {
				
				if(ds==null)
				  connection = getDataSource().getConnection();
				else
				connection = ds.getConnection();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}*/
		
		
		try
		{
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/facturacion");
		connection = ds.getConnection();
		}
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public boolean isStringEmpty(String s) {
		return s == null || s.trim().equals("");
	}
	
	public abstract void save(T target);
	
	public abstract void update(T target);
	
	public abstract void delete(T target);
	
	public abstract List<T> findAll();
	
	public abstract List<T> findAll(Integer firstRow, Integer maxRows);
}
