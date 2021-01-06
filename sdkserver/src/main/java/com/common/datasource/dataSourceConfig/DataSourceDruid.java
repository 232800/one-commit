package com.common.datasource.dataSourceConfig;


import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
public class DataSourceDruid {

    private static Logger logger = LogManager.getLogger(DataSourceDruid.class);

    @Autowired
    private DataSourceConfig selfDataSourceConfig;

    @Bean(name = "selfDataSource")
    public DruidDataSource selfDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(selfDataSourceConfig.getDriverClassName());
        druidDataSource.setUrl(selfDataSourceConfig.getUrl());
        druidDataSource.setUsername(selfDataSourceConfig.getUname());
        druidDataSource.setPassword(selfDataSourceConfig.getPassword());
        druidDataSource.setInitialSize(selfDataSourceConfig.getInitialSize());
        druidDataSource.setMinIdle(selfDataSourceConfig.getMinIdle());
        druidDataSource.setMaxActive(selfDataSourceConfig.getMaxActive());
        druidDataSource.setMaxWait(selfDataSourceConfig.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(selfDataSourceConfig.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(selfDataSourceConfig.getMinEvictableIdleTimeMillis());
        String validationQuery = selfDataSourceConfig.getValidationQuery();
        if (validationQuery != null && !"".equals(validationQuery)) {
            druidDataSource.setValidationQuery(validationQuery);
        }
        druidDataSource.setTestWhileIdle(selfDataSourceConfig.getTestWhileIdle());
        druidDataSource.setTestOnBorrow(selfDataSourceConfig.getTestOnBorrow());
        druidDataSource.setTestOnReturn(selfDataSourceConfig.getTestOnReturn());
        if (selfDataSourceConfig.getPoolPreparedStatements()) {
            druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(selfDataSourceConfig.getMaxPoolPreparedStatementPerConnectionSize());
        }
        try {
            //这是最关键的,否则SQL监控无法生效
            druidDataSource.setFilters(selfDataSourceConfig.getFilters());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        String connectionPropertiesStr = selfDataSourceConfig.getConnectionProperties();
        if (connectionPropertiesStr != null && !"".equals(connectionPropertiesStr)) {
            Properties connectProperties = new Properties();
            String[] propertiesList = connectionPropertiesStr.split(";");
            for (String propertiesTmp : propertiesList) {
                String[] obj = propertiesTmp.split("=");
                String key = obj[0];
                String value = obj[1];
                connectProperties.put(key, value);
            }
            druidDataSource.setConnectProperties(connectProperties);
        }
        druidDataSource.setUseGlobalDataSourceStat(selfDataSourceConfig.getUseGlobalDataSourceStat());
        return druidDataSource;
    }


    /**
     * 注册一个druid StatViewServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        //servletRegistrationBean.addInitParameter("deny","127.0.0.1");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个druid filterRegistrationBean
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
