package com.common.datasource.mybatisConfig;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.common.utils.YmlUtils;

@Configuration
//由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(MybatisConfig.class)
public class MapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("selfSqlSessionFactory");
        //每张表对应的*.java,interface类型的Java文件
        mapperScannerConfigurer.setBasePackage(YmlUtils.getSelfBasePackage(""));
        return mapperScannerConfigurer;
    }
}