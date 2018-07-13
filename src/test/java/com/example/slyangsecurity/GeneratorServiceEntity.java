package com.example.slyangsecurity;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class GeneratorServiceEntity {

	@Test
	public void generateCode() {
		String packageName = "com.baomidou.springboot";
		boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
		
		generateByTables(serviceNameStartWithI, packageName, "bc_chaincode");
	}

	private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
		GlobalConfig config = new GlobalConfig();
		String dbUrl = "jdbc:mysql://localhost:3306/hyht_blockchain";
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL)
				.setUrl(dbUrl)
				.setUsername("root")
				.setPassword("123456")
				.setDriverName("com.mysql.jdbc.Driver");
		StrategyConfig strategyConfig = new StrategyConfig();

		strategyConfig
				.entityTableFieldAnnotationEnable(true)
				.setCapitalMode(true)
				.setEntityLombokModel(false)
				.setDbColumnUnderline(true)
				.setNaming(NamingStrategy.underline_to_camel)
				.setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

		config.setActiveRecord(true)
				.setAuthor("author slyang")
				.setOutputDir("generate")
				.setFileOverride(true);

		if (!serviceNameStartWithI) {
			config.setServiceName("%sService");
		}

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

	private void generateByTables(String packageName, String... tableNames) {
		generateByTables(true, packageName, tableNames);
	}

}
