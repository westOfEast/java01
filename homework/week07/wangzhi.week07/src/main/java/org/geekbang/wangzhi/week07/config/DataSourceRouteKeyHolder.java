package org.geekbang.wangzhi.week07.config;

public class DataSourceRouteKeyHolder {
    private static ThreadLocal<String> routeKeyHolder = new ThreadLocal<>();

    private static final String DEFAULT_ROUTE_KEY = "datasource1";

    public static String getRouteKey(){
        return routeKeyHolder.get();
    }

    public static void setDefault(){
        routeKeyHolder.set(DEFAULT_ROUTE_KEY);
    }

    public static String getDefault(){
        return DEFAULT_ROUTE_KEY;
    }
}
