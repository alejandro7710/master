package com.dsi.capa.db.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceUtil {
	public static final String DATA_SOURCE_NAME = "jdbc/facturacion";
	
	/**
	 * Obtiene del contexto el datasource
	 * @param dataSourceName
	 * @return
	 */
	public static DataSource getDataSource(String dataSourceName) {
		DataSource ds = null;
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup(dataSourceName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public static DataSource getDataSource() {
		return getDataSource(DATA_SOURCE_NAME);
	}
}
