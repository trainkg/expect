package com.barley.robot.mybatis;

import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BubbleMybatisPlugin extends org.mybatis.generator.api.PluginAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean validate(List<String> warnings) {
        if (warnings != null) {
            for (String string : warnings) {
                logger.info("bubble: {}", string);
            }
        }
        return true;
    }
    
    @Override
    public boolean clientInsertMethodGenerated(Method method, TopLevelClass topLevelClass,
                    IntrospectedTable introspectedTable) {
        method.addJavaDocLine("//插入方法");
        return true;
    }
    
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                    IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        field.addJavaDocLine("/**bubble field comments*/");
        return true;
    }
    
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("//bubble java doc");
        topLevelClass.addFileCommentLine("//bubble comment line");
        return true;
    }
    
    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        return super.contextGenerateAdditionalJavaFiles();
    }
}
