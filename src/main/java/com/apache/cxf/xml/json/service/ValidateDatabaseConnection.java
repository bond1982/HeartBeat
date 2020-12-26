package com.apache.cxf.xml.json.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;

public class ValidateDatabaseConnection {
	@SuppressWarnings("unused")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public int getValidateDatabaseConnection() throws Exception {
		String SQL = null;
		Integer count=null;
		String databaseType=getDataBaseType();
		if(databaseType!=null) {
			if ("Oracle".equals(getDataBaseType())) {
				SQL = "SELECT 1 FROM DUAL";
			} else {
				SQL = "SELECT 1";
			}
			count = jdbcTemplateObject.queryForInt(SQL);
		}else {
			count=-1;
		}
		return count;
	}

	/**
	 * Get the database type
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getDataBaseType() throws Exception {
		DataSource dataSource=jdbcTemplateObject.getDataSource();
		return (String) JdbcUtils.extractDatabaseMetaData(dataSource, "getDatabaseProductName");
	}
	
}
