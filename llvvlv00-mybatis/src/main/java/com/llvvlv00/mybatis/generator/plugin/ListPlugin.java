package com.llvvlv00.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.VisitableElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

public class ListPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement rootElement = document.getRootElement();
        rootElement.addElement(replaceCondition(introspectedTable.getFullyQualifiedTable().getIntrospectedTableName()));
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private TextElement replaceCondition(String tableName) {
        String node="<select id=\"list\" parameterType=\"com.llvvlv00.mybatis.help.MyBatisWrapper\" resultMap=\"BaseResultMap\">\n" +
                "       <include refid=\"listSql\" />\n" +
                "    </select>\n" +
                "    <sql id=\"listSql\"> \n" +
                "       select \n" +
                "       <if test=\"selectBuilder == null\">\n" +
                "           *\n"+
                "       </if>\n" +
                "       <if test=\"selectBuilder != null\">\n" +
                "           <trim prefixOverrides=\",\" suffixOverrides=\",\"> \n"+
                "               ${selectBuilder}\n"+
                "           </trim>\n"+
                "       </if>\n"+
                "       from " + tableName + "\n"+
                "       <if test=\"_parameter != null\">\n"+
                "           <include refid=\"Example_Where_Clause\" />\n"+
                "       </if>\n"+
                "       <if test=\"groupSql != null\">\n" +
                "           group by ${groupSql}\n"+
                "       </if>\n"+
                "       <if test=\"orderByClause != null\">\n" +
                "           order by ${orderByClause}\n"+
                "       </if>\n"+
                "       <if test=\"rows != null\">\n"+
                "           <if test=\"offset != null\">\n"+
                "               limit ${offset}, ${rows}\n"+
                "           </if>\n"+
                "           <if test=\"offset == null\">\n"+
                "               limit ${rows} \n"+
                "           </if>\n"+
                "       </if>\n"+
                "    </sql>"  ;
        return new TextElement(node);
    }
}
