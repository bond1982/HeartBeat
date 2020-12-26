package com.apache.cxf.xml.json.service;

import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

public class HeartBeatServiceImpl implements IHeartBeatService{
	@Autowired
	private ValidateDatabaseConnection validateDatabaseConnection;
	@Autowired
	Map<String, DataSource> dataSourceMap;
	@Override
	public Response getHeartBeatStatus() {
		Boolean isValid = validateDataSource();
		if(isValid) {
			String json = "Application is up and runnig";
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}else {
			return Response.serverError().entity("Application is not working properly").build();
		}
	}
	private boolean validateDataSource() {
		Set<String> datasourceNames=dataSourceMap.keySet();
		boolean isValid=true;
		for (String dataSourceName : datasourceNames) {
			validateDatabaseConnection.setDataSource(dataSourceMap.get(dataSourceName));
			try {
				Integer result=validateDatabaseConnection.getValidateDatabaseConnection();
				if(result==1) {
					isValid=true;
				}
			}catch( javax.naming.NameNotFoundException ex) {
				isValid=true;
			}catch (Exception e) {
				e.printStackTrace();
				isValid=false;
				break;
			}
		}
		return isValid;
	}
}
