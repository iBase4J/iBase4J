package org.ibase4j.core.generator.plugin;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 生成 Mapper 类
 * 
 * @author ShenHuaJie
 * @version 2016年6月24日 下午6:47:06
 */
public class MapperPlugin extends PluginAdapter {

	private static final String DEFAULT_DAO_SUPER_CLASS = "org.ibase4j.core.base.BaseMapper";
	private static final String DEFAULT_EXPAND_DAO_SUPER_CLASS = "org.ibase4j.core.base.BaseExpandMapper";
	private String daoTargetDir;
	private String daoTargetPackage;

	private String daoSuperClass;

	// 扩展
	private String expandDaoTargetPackage;
	private String expandDaoSuperClass;

	private ShellCallback shellCallback = null;

	public MapperPlugin() {
		shellCallback = new DefaultShellCallback(false);
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

		expandDaoTargetPackage = properties.getProperty("expandTargetPackage");
		expandDaoSuperClass = properties.getProperty("expandDaoSuperClass");
		if (!stringHasValue(expandDaoSuperClass)) {
			expandDaoSuperClass = DEFAULT_EXPAND_DAO_SUPER_CLASS;
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

			GeneratedJavaFile mapperJavafile = null;
			if (shortName.endsWith("Mapper")) { // 扩展Mapper
				if (stringHasValue(expandDaoTargetPackage)) {
					Interface mapperInterface = new Interface(
							expandDaoTargetPackage + "." + shortName.replace("Mapper", "ExpandMapper"));
					mapperInterface.setVisibility(JavaVisibility.PUBLIC);
					mapperInterface.addJavaDocLine("/**");
					mapperInterface.addJavaDocLine(" * " + shortName + "扩展");
					mapperInterface.addJavaDocLine(" */");

					FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(expandDaoSuperClass);
					mapperInterface.addImportedType(daoSuperType);
					mapperInterface.addSuperInterface(daoSuperType);

					mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
					try {
						File mapperDir = shellCallback.getDirectory(daoTargetDir, daoTargetPackage);
						File mapperFile = new File(mapperDir, mapperJavafile.getFileName());
						// 文件不存在
						if (!mapperFile.exists()) {
							mapperJavaFiles.add(mapperJavafile);
						}
					} catch (ShellException e) {
						e.printStackTrace();
					}
				}
			} else if (!shortName.endsWith("Example")) { // CRUD Mapper
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

				mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
				mapperJavaFiles.add(mapperJavafile);
			}
		}
		return mapperJavaFiles;
	}
}
