package com.example.slyangsecurity;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

public class MybatisGenerator {

    private final static String PACKAGE_NAME = "com.example.slyangsecurity.modules.block";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/hyht_blockchain?useSSL=true";
    private final static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "123456";

    private final static String CREATE_TABLE_NAME = "bc_chaincode";

    @Test
    public void generateCode() {
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        generateByTables(serviceNameStartWithI, PACKAGE_NAME, CREATE_TABLE_NAME);
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {

        GlobalConfig config = new GlobalConfig()
                .setActiveRecord(false)
                .setAuthor("author slyang")
                .setDateType(DateType.ONLY_DATE)
                .setOutputDir("generate")
                //.setIdType(IdType.UUID)  主键生成
                .setServiceName("%sService")
                .setEntityName("%sEntity")
                .setFileOverride(true);

        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .setUrl(DB_URL)
                .setUsername(DB_USERNAME)
                .setPassword(DB_PASSWORD)
                .setDriverName(DB_DRIVER);

        StrategyConfig strategyConfig = new StrategyConfig()
                .entityTableFieldAnnotationEnable(true) // 生成 字段注解
                .setCapitalMode(false)    // 是否大写命名 ? TODO
                .setEntityBuilderModel(true)  // 对象为建造者模型
                .setRestControllerStyle(true)  // restcontroller
                .setNaming(NamingStrategy.underline_to_camel) // 下划线转驼峰命名 表名
                .setColumnNaming(NamingStrategy.underline_to_camel) // 下划线转驼峰命名 字段名
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        new AutoGenerator()
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();


    }

}
