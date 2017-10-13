package org.ibase4j.core.config;

import java.util.List;

import javax.sql.DataSource;

import org.ibase4j.core.support.scheduler.JobListener;
import org.ibase4j.core.support.scheduler.SchedulerManager;
import org.ibase4j.core.util.InstanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerConfig {
	@Autowired
	Environment env;

	@Bean
	public SchedulerFactoryBean schedulerFactory(DataSource dataSource) {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setSchedulerName("iBase4J-Scheduler");
		schedulerFactory.setApplicationContextSchedulerContextKey("applicationContext");
		schedulerFactory.setDataSource(dataSource);
		Resource resouce = new DefaultResourceLoader().getResource("classpath:quartz.properties");
		schedulerFactory.setConfigLocation(resouce);
		return schedulerFactory;
	}

	@Bean
	public SchedulerManager scheduler(SchedulerFactoryBean schedulerFactory, JobListener listener) {
		SchedulerManager schedulerManager = new SchedulerManager();
		schedulerManager.setScheduler(schedulerFactory.getScheduler());
		List<org.quartz.JobListener> jobListeners = InstanceUtil.newArrayList();
		jobListeners.add(listener);
		schedulerManager.setJobListeners(jobListeners);
		return schedulerManager;
	}

	@Bean
	public JobListener jobListener() {
		return new JobListener();
	}
}
