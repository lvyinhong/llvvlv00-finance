package com.llvvlv00.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.VisitableElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

public class UpdateByExampleWhereClausePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {

        return true;
    }
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement rootElement = document.getRootElement();
        rootElement.addElement(replaceCondition());
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private TextElement replaceCondition() {
        String node = "<sql id=\"Update_By_Example_Where_Clause\">\n" +
                "   <where>\n" +
                "       <trim prefixOverrides=\"and | or\">\n" +
                "           <foreach>" +
                "               <if test=\"criteria.andOrOr\">\n "+
                "                   or\n" +
                "               </if>\n" +
                "               <if test=\"criteria.valid\">\n"+
                "                   <trim prefix=\"(\" prefixOverrides=\"and\" suffix=\")\">\n" +
                "                       <foreach collection=\"criteria.criteria\" item=\"criterion\">\n"+
                "                           <choose>\n"+
                "                               <when test=\"criterion.noValue\">\n" +
                "                                   ${criterion.condition} #{criterion.value,jdbcType=${criterion.jdbcType}}\n"+
                "                               </when>\n"+
                "                               <when test=\"criterion.betweenValue\"> \n"+
                "                                   ${criterion.condition}\n" +
                "                                   <foreach close=\")\" collection=\"criterion.value\" item=\"listItem\" open=\"(\" separator=\",\">\n"+
                "                                       #{listItem,jdbcType=${criterion.jdbcType}}\n" +
                "                                   </foreach>\n"+
                "                               </when>\n"+
                "                           </choose>\n"+
                "                       </foreach>\n"+
                "                   </trim>\n"+
                "               </if>\n"+
                "           </foreach>\n" +
                "       </trim>\n" +
                "   </where>\n" +
                "</sql>";
        return new TextElement(node);
    }
}
