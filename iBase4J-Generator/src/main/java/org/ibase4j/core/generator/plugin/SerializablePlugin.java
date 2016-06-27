package org.ibase4j.core.generator.plugin;

import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @author ShenHuaJie
 * @version 2016年6月24日 下午6:42:28
 */
public class SerializablePlugin extends PluginAdapter {

	private FullyQualifiedJavaType serializable;

	public SerializablePlugin() {
		super();
		serializable = new FullyQualifiedJavaType("java.io.Serializable");
	}

	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	protected void makeSerializable(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (topLevelClass.getSuperClass() != null) {
			String superName = topLevelClass.getSuperClass().getFullyQualifiedName();
			if ("".equals(superName.trim())) {
				topLevelClass.addImportedType(serializable);
				topLevelClass.addSuperInterface(serializable);
			}
		} else {
			topLevelClass.addImportedType(serializable);
			topLevelClass.addSuperInterface(serializable);
		}
		topLevelClass.addAnnotation("@SuppressWarnings(\"serial\")");
	}
}
