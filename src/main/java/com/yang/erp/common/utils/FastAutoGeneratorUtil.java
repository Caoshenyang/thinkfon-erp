package com.yang.erp.common.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 代码生成器
 * </p>
 *
 * @author 曹申阳
 * @since 2022-09-23 16:52:05
 */
public class FastAutoGeneratorUtil {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://42.192.40.128:3306/erp?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai", "root", "root@123456")
            .dbQuery(new MySqlQuery())
            .schema("erp")
            .typeConvert(new MySqlTypeConvert() {
                @Override
                public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                    // tinyint 转换成 int
                    if (fieldType.toLowerCase().contains("tinyint")) {
                        return DbColumnType.INTEGER;
                    }
                    return super.processTypeConvert(config, fieldType);
                }
            })
            .keyWordsHandler(new MySqlKeyWordsHandler());

    /**
     * 执行 run
     */
    public static void main(String[] args) {

        AtomicReference<String> loadModuleName = new AtomicReference<>("");
        // 获取当前路径
        String path = System.getProperty("user.dir");
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> {
                    // 设置模块名称
                    loadModuleName.set(scanner.apply("请输入当前模块名称："));
                    // 设置作者
                    builder.author("曹申阳")
                            .disableOpenDir()
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            // 指定输出目录
                            .outputDir(path + "\\" + loadModuleName.get() + "\\src\\main\\java");
                })
                // 包配置/
                .packageConfig(builder -> {
                    builder.parent("com.yang") // 设置父包名
                            .moduleName("erp") // 设置父包模块名
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .controller("controller")
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, path + "\\" + loadModuleName.get() + "\\src\\main\\resources\\mapper"));
                })
                // 策略配置
                .strategyConfig((scanner, builder) -> {
                    // 设置需要生成的表名
                    builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                            .addTablePrefix("blog_", "sys_", "t_") // 设置过滤表前缀
                            .entityBuilder() // 设置 entity 生成规则
                            //.superClass(BaseEntity.class) //entity 继承父类
                            //.addSuperEntityColumns("id", "create_time", "update_time", "create_by", "update_by", "del_flag") //添加父类公共字段
                            .addTableFills(new Column("create_time", FieldFill.INSERT),new Column("create_by", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE),new Column("update_by", FieldFill.INSERT))
                            .logicDeleteColumnName("del_flag")
                            .idType(IdType.AUTO)
                            .enableLombok()// lombok 注解
                            .controllerBuilder() // 设置 controller 生成规则
                            .enableRestStyle() // 开启生成@RestController 控制器
                            .build();
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }


    /**
     * 处理 all 情况
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
