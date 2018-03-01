package org.ibase4j.core.config;

import java.util.ArrayList;
import java.util.List;
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

import top.ibase4j.core.util.PropertiesUtil;

@Configuration
public class DataSourceConfig {
	@Bean // 声明其为Bean实例
	@Primary // 在同样的DataSource中，首先使用被标注的DataSource
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
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
	public ServletRegistrationBean<StatViewServlet> druidServlet() {
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>();
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
