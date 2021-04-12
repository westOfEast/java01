package org.geekbang.wangzhi.week07.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DefaultDataSource extends AbstractRoutingDataSource {

    public DefaultDataSource(DataSourceConfig dataSourceConfig) throws Exception {
        super();
        if(dataSourceConfig.getMap() == null || dataSourceConfig.getMap().size() == 0){
            throw new Exception("no data source config");
        }

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceConfig.getMap().entrySet()
                .stream()
                .forEach(entry -> dataSourceMap.put(entry.getKey(),generateDataSource(entry.getValue())));
        super.setTargetDataSources(dataSourceMap);
        super.setDefaultTargetDataSource(dataSourceMap.get(DataSourceRouteKeyHolder.getDefault()));
        super.afterPropertiesSet();
    }

    private DataSource generateDataSource(DataSourceBean bean){
        DataSource dataSource = DataSourceBuilder.create()
                .url(bean.getUrl())
                .type(HikariDataSource.class)
                .username(bean.getUsername())
                .password(bean.getPassword())
                .driverClassName(bean.getDriverClassName())
                .build();
        return dataSource;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceRouteKeyHolder.getRouteKey();
    }
}
