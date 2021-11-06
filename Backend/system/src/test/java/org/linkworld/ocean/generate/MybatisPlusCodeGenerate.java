package org.linkworld.ocean.generate;

/*
 *@Author  liu
 *@Since   2021/11/4  17:10
 */



import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

public class MybatisPlusCodeGenerate {


    @Test
   public void generateCode(){
       // 代码生成器，构建一个代码自动生成器：
        AutoGenerator generator = new AutoGenerator();

        // 配置策略
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        // 设置不打开我的电脑
        gc.setOpen(false);
        // 设置是否覆盖原有的东西
        gc.setFileOverride(false);
        // 用正则表达式去掉Service的I前缀
        gc.setServiceName("%sService");
        // 设置全局唯一id，雪花算法
        gc.setIdType(IdType.ASSIGN_ID);
        gc.setDateType(DateType.ONLY_DATE);
        // 设置日志
        gc.setSwagger2(true);
        generator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/ocean?useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC");
        generator.setDataSource(dataSourceConfig);

        // 包的配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("org.linkworld.ocean");
        packageConfig.setEntity("pojo");
        packageConfig.setMapper("dao");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        generator.setPackageInfo(packageConfig);

        // 策略的配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 这个非常重要，设置要映射的数据库表名，可变形参个数
        strategyConfig.setInclude("ocean_sensor");
        // 设置名字驼峰命名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 设置字段驼峰命名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // 逻辑删除
        strategyConfig.setLogicDeleteFieldName("deleted");
        // 自动填充
        ArrayList<TableFill> tableFillList = new ArrayList<>();
        TableFill tableFill1 = new TableFill("gmtCreate", FieldFill.INSERT);
        TableFill tableFill2 = new TableFill("gmtModified", FieldFill.INSERT_UPDATE);
        tableFillList.add(tableFill1);
        tableFillList.add(tableFill2);
        strategyConfig.setTableFillList(tableFillList );
        // Lombok
        strategyConfig.setEntityLombokModel(true);
        // 乐观锁
        strategyConfig.setVersionFieldName("version");
        // ControllerRestFullStyle
        strategyConfig.setRestControllerStyle(true);
        // localhost:8080/hello_id_1
        strategyConfig.setControllerMappingHyphenStyle(true);
        generator.setStrategy(strategyConfig);

        // 执行
        generator.execute();
    }

}
