//package com.example.service001.wenfeng;
//
//import javax.inject.Qualifier;
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//@Configuration
//@MapperScan(basePackages="wenfeng",sqlSessionFactoryRef="DBDataSqlSessionFactory")
//public class DataSourceConfig {
//	@Bean(name="DBDataSource")
//	@ConfigurationProperties(prefix="spring.datasource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}
//	@Bean(name="DBDataSqlSessionFactory")
//	public SqlSessionFactory sqlSessionFactory(@Qualifier("DBDataSource") DataSource datasource) {
//		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//		bean.setDataSource(datasource);
//		bean.setMapperLocations(
//				new PathMatchingResourcePatternResolver()
//				.getResources("classpath:mybatis/dbMapper.xml"));
//		return bean.getObject();
//	}
//	@Bean(name="DBDataTransactionManager")
//	public DataSourceTransactionManager  transactionManager(@Qualifier("DBDataSource") DataSource datasource) {
//		
//	}
//}
