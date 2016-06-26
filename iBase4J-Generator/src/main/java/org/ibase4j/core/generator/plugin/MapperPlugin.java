package org.ibase4j.core.generator.plugin;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;

/**
 * 生成 Mapper 类
 * 
 * @author ShenHuaJie
 * @version 2016年6月24日 下午6:47:06
 */
public class MapperPlugin extends PluginAdapter {

	private static final String DEFAULT_DAO_SUPER_CLASS = "org.ibase4j.core.base.BaseMapper";
	private String daoTargetDir;

	private String daoTargetPackage;

	private String daoSuperClass;

	public MapperPlugin() {
	}

	public boolean validate(List<String> warnings) {
		daoTargetDir = properties.getProperty("targetProject");
		boolean valid = stringHasValue(daoTargetDir);

		daoTargetPackage = properties.getProperty("targetPackage");
		boolean valid2 = stringHasValue(daoTargetPackage);

		daoSuperClass = properties.getProperty("daoSuperClass");
		if (!stringHasValue(daoSuperClass)) {
			daoSuperClass = DEFAULT_DAO_SUPER_CLASS;
		}
		return valid && valid2;
	}

	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		JavaFormatter javaFormatter = context.getJavaFormatter();
		List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<GeneratedJavaFile>();
		for (GeneratedJavaFile javaFile : introspectedTable.getGeneratedJavaFiles()) {
			CompilationUnit unit = javaFile.getCompilationUnit();
			FullyQualifiedJavaType baseModelJavaType = unit.getType();

			String shortName = baseModelJavaType.getShortName();
			if (shortName.endsWith("Mapper")) {
				continue;
			}
			Interface mapperInterface = new Interface(daoTargetPackage + "." + shortName + "Mapper");

			mapperInterface.setVisibility(JavaVisibility.PUBLIC);
			mapperInterface.addJavaDocLine("/**");
			mapperInterface.addJavaDocLine(" * 由MyBatis Generator工具自动生成，请不要手动修改");
			mapperInterface.addJavaDocLine(" */");

			FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(daoSuperClass);
			// 添加泛型支持
			daoSuperType.addTypeArgument(baseModelJavaType);
			mapperInterface.addImportedType(baseModelJavaType);
			mapperInterface.addImportedType(daoSuperType);
			mapperInterface.addSuperInterface(daoSuperType);

			GeneratedJavaFile mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
			mapperJavaFiles.add(mapperJavafile);
		}
		return mapperJavaFiles;
	}
}
