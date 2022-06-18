package com.cm.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.cm.pojo.BasePojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generator {
    public static void main(String[] args) {
        //代码自动生成嗷，鼎中鼎
        AutoGenerator mpg = new AutoGenerator();
        //构建配置器
        GlobalConfig gc = new GlobalConfig();
        // 设置作者
        gc.setAuthor("Dico");
        //定义项目路径
        String path = System.getProperty("user.dir");
        // 设置输出路径
        gc.setOutputDir(path + "/src/main/java");
        // 加载到生成器中
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        //mysql 8.0 need 'allowPublicKeyRetrieval=true'
        dsc.setUrl("jdbc:mysql://localhost:3306/costmanage?useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.cm");
        pc.setController("controller");
        pc.setMapper("mapper");
        pc.setEntity("pojo");
        pc.setService("service");
        pc.setServiceImpl("service.impl");

        // 可单独设置路径
        Map<String, String> pathInfo = new HashMap<>();
        pathInfo.put("xml_path", path + "/src/main/resources/com/cm/mapper");
        pathInfo.put("entity_path", path + "/src/main/java/com/cm/pojo");
        pathInfo.put("mapper_path", path + "/src/main/java/com/cm/mapper");
        pathInfo.put("service_path", path + "/src/main/java/com/cm/service");
        pathInfo.put("service_impl_path", path + "/src/main/java/com/cm/service/impl");
        pathInfo.put("controller_path", path + "/src/main/java/com/cm/controller");
        pc.setPathInfo(pathInfo);
        mpg.setPackageInfo(pc);

        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//
//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 如果模板引擎是 velocity
//        // String templatePath = "/templates/mapper.xml.vm";
//
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return path + "/src/main/resources/com/cm/mapper/"
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 下划线转化成驼峰状（小写接大写）
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(BasePojo.class);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        //此处是数据库的表名
        strategy.setInclude("cm_procedure_energy");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        //生成代码
        mpg.execute();
    }
}
