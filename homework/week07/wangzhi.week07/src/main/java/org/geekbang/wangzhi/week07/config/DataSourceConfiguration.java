package org.geekbang.wangzhi.week07.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(prefix = "spring.multi",name = "dataSource",havingValue = "true",matchIfMissing = false)
public class DataSourceConfiguration {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Bean("multiDataSource")
    public DataSource getDataSource() throws Exception {
        return new DefaultDataSource(dataSourceConfig);
    }

    @Bean("sqlSessionFactory")
    @ConditionalOnMissingBean(name = "sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath*:mapping/*.xml"));
        return factoryBean.getObject();
    }


    @Bean("transactionManager")
    @ConditionalOnMissingBean(name = "transactionManager")
    public PlatformTransactionManager getTransactionManager() throws Exception {
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(getDataSource());
        return transactionManager;
    }
}
