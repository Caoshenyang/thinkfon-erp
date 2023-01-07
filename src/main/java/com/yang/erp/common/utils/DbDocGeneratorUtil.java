package com.yang.erp.common.utils;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;

/**
 * <p>
 * 数据库文档生成工具类
 * </p>
 *
 * @author 曹申阳
 * @since 2022-09-25 15:31:10
 */
public class DbDocGeneratorUtil {

    public static void main(String[] args) throws SQLException {
        buildDoc();
    }


    /**
     * 配置想要生成数据表和想要忽略的数据表
     */
    private static ProcessConfig getProcessConfig() {
        // 想要忽略的数据表
//        List<String> ignoreTabName = Collections.singletonList("undo_log");
        // 忽略表前缀
//        List<String> ignorePrefix = Arrays.asList("a", "b");
        // 忽略表后缀
//        List<String> ignoreSuffix = Arrays.asList("_test", "_Test");
        return ProcessConfig.builder()
                // 根据名称指定表生成
                .designatedTableName(Collections.emptyList())
                // 根据表前缀生成
                .designatedTablePrefix(Collections.emptyList())
                .designatedTableSuffix(Collections.emptyList())
//                .ignoreTableName(ignoreTabName)
//                .ignoreTablePrefix(ignorePrefix)
//                .ignoreTableSuffix(ignoreSuffix)
                .build();

    }

    public static void buildDoc() throws SQLException {

        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://42.192.40.128:3306/erp?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai")
                .username("root")
                .password("root@123456")
//                .url("jdbc:mysql://192.168.10.59:3306/erp?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai")
//                .username("root")
//                .password("123456")
                .build();

        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir(System.getProperty("user.dir"))
                .openOutputDir(false)
                .fileType(EngineFileType.HTML)
                .produceType(EngineTemplateType.freemarker)
                .build();

        // 生成文档配置
        Configuration config = Configuration.builder()
                .version("1.0.0")
                .description("mysql")
                .dataSource(dataSource)
                .engineConfig(engineConfig)
                .produceConfig(getProcessConfig())
                .build();
        // 执行生成
        new DocumentationExecute(config).execute();
    }
}
