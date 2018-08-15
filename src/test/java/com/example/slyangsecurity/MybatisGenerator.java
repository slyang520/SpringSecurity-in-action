package com.example.slyangsecurity;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class MybatisGenerator {

    private final static String PACKAGE_NAME = "com.example.slyangsecurity.modules.block";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/hyht_blockchain";
    private final static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "123456";

    private final static String CREATE_TABLE_NAME = "test_table";

    @Test
    public void generateCode() {
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        generateByTables(serviceNameStartWithI, PACKAGE_NAME, CREATE_TABLE_NAME);
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig()
                .setActiveRecord(false)
                .setAuthor("author slyang")
                .setOutputDir("generate")
                .setServiceName("%sService")
                .setFileOverride(true);

        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .setUrl(DB_URL)
                .setUsername(DB_USERNAME)
                .setPassword(DB_PASSWORD)
                .setDriverName(DB_DRIVER);

        StrategyConfig strategyConfig = new StrategyConfig()
                .entityTableFieldAnnotationEnable(true)
                .setCapitalMode(true)
                .setEntityBuilderModel(true)
                .setDbColumnUnderline(false)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        
        new AutoGenerator().setGlobalConfig(config)
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
