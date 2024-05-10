package com.llvvlv00.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.List;

public class InitModelDefaultValuePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Method initDefaultMethod = new Method("initDefault");
        initDefaultMethod.setVisibility(JavaVisibility.PUBLIC);
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : allColumns) {
            String defaultValue = column.getDefaultValue();
            if (column.getDefaultValue() != null && "null".equals(column.getDefaultValue())) {
                defaultValue = "null";
            }
            if (column.getDefaultValue() != null && !"null".equals(column.getDefaultValue()) && "java.lang.String".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = "\"" + defaultValue + "\"";
            }
            if (column.getDefaultValue() != null && "java.lang.Long".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = defaultValue + "L";
            }
            if (column.getDefaultValue() != null && "java.lang.Boolean".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = Boolean.toString(Boolean.parseBoolean(defaultValue));
            }
            if (column.getDefaultValue() != null && "java.lang.Byte".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = "(byte) " + defaultValue ;
            }
            if (column.getDefaultValue() != null && "java.util.Date".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = "new Date()";
                topLevelClass.addImportedType("java.util.Date");
            }
            if (column.getDefaultValue() != null && "java.math.BigDecimal".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = "new BigDecimal(\""+ defaultValue +"\")";
            }
            if (defaultValue != null) {
                initDefaultMethod.addBodyLine("if (this.get" + JavaBeansUtil.getCamelCaseString(column.getActualColumnName().replaceFirst("is_",""), true) + "() == null) {");
                initDefaultMethod.addBodyLine("this.set" + JavaBeansUtil.getCamelCaseString(column.getActualColumnName().replaceFirst("is_",""), true) + "(" + defaultValue + ");");
                initDefaultMethod.addBodyLine("}");
            }
        }
        topLevelClass.addMethod(initDefaultMethod);

        Method initUpdateMethod = new Method("initUpdate");
        initUpdateMethod.setVisibility(JavaVisibility.PUBLIC);
        allColumns = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : allColumns) {
            String defaultValue = column.getDefaultValue();
            if (column.getDefaultValue() != null && "null".equals(column.getDefaultValue())) {
                defaultValue = "null";
            }
            if (column.getDefaultValue() != null && !"null".equals(column.getDefaultValue()) && "java.lang.String".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = "\"" + defaultValue + "\"";
            }
            if (column.getDefaultValue() != null && "java.lang.Long".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = defaultValue + "L";
            }
            if (column.getDefaultValue() != null && "java.lang.Boolean".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = Boolean.toString(Boolean.parseBoolean(defaultValue));
            }
            if (column.getDefaultValue() != null && "java.lang.Byte".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = "(byte) " + defaultValue ;
            }
            if (column.getDefaultValue() != null && "java.util.Date".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = "new Date()";
                topLevelClass.addImportedType("java.util.Date");
            }
            if (column.getDefaultValue() != null && "java.math.BigDecimal".equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())) {
                defaultValue = "new BigDecimal(\""+ defaultValue +"\")";
            }
            if (defaultValue != null) {
                initDefaultMethod.addBodyLine("if (this.get" + JavaBeansUtil.getCamelCaseString(column.getActualColumnName().replaceFirst("is_",""), true) + "() == null) {");
                initDefaultMethod.addBodyLine("this.set" + JavaBeansUtil.getCamelCaseString(column.getActualColumnName().replaceFirst("is_",""), true) + "(" + defaultValue + ");");
                initDefaultMethod.addBodyLine("}");
            }
        }

        topLevelClass.addMethod(initUpdateMethod);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }
}
