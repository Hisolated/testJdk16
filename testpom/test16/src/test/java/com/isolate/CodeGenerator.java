//package com.isolate;
//
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import com.tmzh.ppd.base.entity.BaseDomain;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Description: 代码自动生成功能类
// */
//public class CodeGenerator {
//
//    public static void main(String[] args) {
//        //代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        //全局配置
//        GlobalConfig gc = new GlobalConfig();
//        //String projectPath = System.getProperty("user.dir");
//        String projectPath = "F:/workspace/RiBao/propaganda-eb/propaganda-be/module_business/module_business_base";
//        //        String projectPath = "F:/guniuworkspace/mes3.0/backend/auth";
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("isolate");
//        gc.setFileOverride(true);     //是否覆蓋已有文件 默认值：false
//        gc.setSwagger2(true);          // swaggerApi注释
//        gc.setOpen(false);           //是否打开输出目录 默认值:true
//        gc.setEnableCache(false);    // XML 二级缓存
//        gc.setBaseResultMap(true);   // XML ResultMap
//        gc.setBaseColumnList(true);  // XML columList
////        gc.setIdType(IdType.AUTO);
//        mpg.setGlobalConfig(gc);
//
//        //数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://10.200.83.35:3306/sx_dev_ppd?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("ppddev");
//        dsc.setPassword("Ppddev*168");
//        mpg.setDataSource(dsc);
//
//        //包配置
//        PackageConfig pc = new PackageConfig();
//        //设置生产代码的地方，一般是业务bus 模块
//        pc.setModuleName("business");
//        pc.setParent("com.tmzh.ppd");
//        mpg.setPackageInfo(pc);
//
//        //自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                //自定义输入文件名称
//                return projectPath + "/src/main/java/com/tmzh/ppd/business/mapper/xml/"
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//        //这里还可以设置模板的配置
//        mpg.setTemplate(new TemplateConfig().setXml(null));
//
//        //生成策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        //类名生成策略：驼峰命名
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        //字段名生成方式：驼峰命名
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        //配置继承的父类
//        strategy.setSuperEntityClass(BaseDomain.class);
//        //去掉父类属性
//        strategy.setSuperEntityColumns("auto_pk","id","created_by","created_at","updated_by","updated_at","version","deleted","org_id","tenant_id","section_id");
//
////        strategy.setSuperControllerClass("com.guniugroup.mes.common.base.BaseController");
//        strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
//        //需要生成的表
//        strategy.setInclude("report_accurate_portrait");
//
//        //strategy.setSuperEntityColumns("id");
//        strategy.setControllerMappingHyphenStyle(true);
//        //过滤表前缀
////        strategy.setTablePrefix("sys_");
//
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//    }
//}
