package com.apache.cxf.xml.json.service;

import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DataSourceDiscoveryBean implements InitializingBean,ApplicationContextAware {
	
	private ApplicationContext context;
	
	private Map<String, DataSource> dataSourceMap;
	
	public Map<String, DataSource> getDataSourceMap() {
		return dataSourceMap;
	}
	public void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
		this.dataSourceMap = dataSourceMap;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, DataSource> datasourceMap= context.getBeansOfType(javax.sql.DataSource.class);
		Set<String> keys=datasourceMap.keySet();
		for (String datasourceId : keys) {
			DataSource dataSource=datasourceMap.get(datasourceId);
			if(dataSource!=null) {
				dataSourceMap.put(datasourceId,dataSource);
			}
		}
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;		
	}

}
