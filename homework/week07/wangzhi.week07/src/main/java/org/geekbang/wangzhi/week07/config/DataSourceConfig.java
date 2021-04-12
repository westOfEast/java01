package org.geekbang.wangzhi.week07.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(value = "spring.multi")
public class DataSourceConfig {
    private Map<String,DataSourceBean> map;
}
