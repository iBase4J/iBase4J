package org.ibase4j.core.config;

import javax.sql.DataSource;

import org.ibase4j.core.util.PropertiesUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.AutoSqlInjector;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;

@Configuration
@MapperScan("org.ibase4j.mapper*")
@EnableTransactionManagement(proxyTargetClass = true)
public class MyBatisConfig {

	/**
	 * 根据数据源创建SqlSessionFactory
	 */
	@Bean(name = "sqlSessionFactory")
	public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
		MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources(PropertiesUtil.getString("mybatis.mapperLocations")));
		sessionFactory.setTypeAliasesPackage(PropertiesUtil.getString("mybatis.typeAliasesPackage"));

		GlobalConfiguration config = new GlobalConfiguration();
		config.setDbColumnUnderline(true);
		config.setSqlInjector(new AutoSqlInjector());
		sessionFactory.setGlobalConfig(config);

		return sessionFactory;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
