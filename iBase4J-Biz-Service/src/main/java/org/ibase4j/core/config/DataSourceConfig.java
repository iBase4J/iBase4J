package org.ibase4j.core.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

import top.ibase4j.core.support.dbcp.ChooseDataSource;
import top.ibase4j.core.util.InstanceUtil;
import top.ibase4j.core.util.PropertiesUtil;

@Configuration
public class DataSourceConfig {

	@Bean // 声明其为Bean实例
	@Primary // 在同样的DataSource中，首先使用被标注的DataSource
	public DataSource dataSource() {
		DataSource write = getDataSource(false);
		DataSource read = getDataSource(true);
		Map<Object, Object> targetDataSources = InstanceUtil.newHashMap("write", write);
		targetDataSources.put("read", read);

		ChooseDataSource dataSource = new ChooseDataSource();
		dataSource.setDefaultTargetDataSource(write);
		dataSource.setTargetDataSources(targetDataSources);
		Map<String, String> method = InstanceUtil.newHashMap();
		method.put("write", ",add,insert,create,update,delete,remove,");
		method.put("read", ",get,select,count,list,query,");
		dataSource.setMethodType(method);
		return dataSource;
	}

	public DataSource getDataSource(boolean readOnly) {
		DruidDataSource datasource = new DruidDataSource();
		if (readOnly) {
			datasource.setUrl(PropertiesUtil.getString("druid.reader.url"));
			datasource.setUsername(PropertiesUtil.getString("druid.reader.username"));
			datasource.setPassword(PropertiesUtil.getString("druid.reader.password"));
		} else {
			datasource.setUrl(PropertiesUtil.getString("druid.writer.url"));
			datasource.setUsername(PropertiesUtil.getString("druid.writer.username"));
			datasource.setPassword(PropertiesUtil.getString("druid.writer.password"));
		}
		datasource.setDefaultReadOnly(readOnly);
		// configuration
		Properties properties = new Properties();
		properties.putAll(PropertiesUtil.getProperties());
		datasource.configFromPropety(properties);

		List<Filter> filters = new ArrayList<>();
		filters.add(statFilter());
		filters.add(wallFilter());
		datasource.setProxyFilters(filters);

		return datasource;
	}

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");
		return servletRegistrationBean;
	}

	public StatFilter statFilter() {
		StatFilter statFilter = new StatFilter();
		statFilter.setLogSlowSql(true);
		statFilter.setMergeSql(true);
		statFilter.setSlowSqlMillis(1000);

		return statFilter;
	}

	public WallFilter wallFilter() {
		WallFilter wallFilter = new WallFilter();

		// 允许执行多条SQL
		WallConfig config = new WallConfig();
		config.setMultiStatementAllow(true);
		wallFilter.setConfig(config);

		return wallFilter;
	}
}
