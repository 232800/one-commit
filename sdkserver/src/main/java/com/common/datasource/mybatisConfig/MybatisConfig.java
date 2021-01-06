package com.common.datasource.mybatisConfig;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSource;
import com.common.utils.YmlUtils;

@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

    @Resource(name = "selfDataSource")
    private DruidDataSource selfDataSource;

    /**
     * 配置mybatis sqlSessionFactory
     *
     * @return
     */
    @Bean(name = "selfSqlSessionFactory")
    public SqlSessionFactory selfSqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(selfDataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
            //每张表对应的xml文件
            bean.setMapperLocations(resolver.getResources(YmlUtils.getSelfMapperLocation("")));
            return bean.getObject();
        } catch (Exception e) {
            logger.error("init selfSqlSessionFactory error:", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 配置mybatis sqlSessionTemplate
     *
     * @return
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory selfSqlSessionFactory) {
        return new SqlSessionTemplate(selfSqlSessionFactory);
    }

    /**
     * 配置事务
     */
    @Resource(name = "transactionManager")
    private PlatformTransactionManager transactionManager;

    //创建事务管理器
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(selfDataSource);
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager;
    }
}
