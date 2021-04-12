package org.geekbang.wangzhi.week07.config;

import lombok.Data;

@Data
public class DataSourceBean {
    private String url;

    private String username;

    private String password;

    private String driverClassName;
}
