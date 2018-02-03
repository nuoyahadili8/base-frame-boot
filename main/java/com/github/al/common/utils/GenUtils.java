package com.github.al.common.utils;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import com.github.al.common.exception.MyException;
import com.github.al.entity.Column;
import com.github.al.entity.Table;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年12月19日 下午11:40:24
 */
public class GenUtils {

	public static List<String> getTemplates(){
		List<String> templates = new ArrayList<String>();
		templates.add("generator/template/Entity.java.vm");
		templates.add("generator/template/Dao.java.vm");
		templates.add("generator/template/Mapper.xml.vm");
		templates.add("generator/template/Service.java.vm");
		templates.add("generator/template/ServiceImpl.java.vm");
		templates.add("generator/template/Controller.java.vm");
		templates.add("generator/template/list.js.vm");
		templates.add("generator/template/list.ftl.vm");
		templates.add("generator/template/add.ftl.vm");
		templates.add("generator/template/edit.ftl.vm");
		templates.add("generator/template/info.ftl.vm");
		templates.add("generator/template/common.ftl.vm");
		templates.add("generator/template/menu.sql.vm");
		return templates;
	}

	/**
	 * 生成代码
	 */
	public static void generatorCode(Map<String, String> table,
			List<Map<String, String>> columns, ZipOutputStream zip){
		//配置信息
		Configuration config = getConfig();

		//表信息
		Table tableEntity = new Table();
		tableEntity.setTableName(table.get("tableName"));
		tableEntity.setComments(table.get("tableComment"));
		//表名转换成Java类名
		String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
		tableEntity.setClassName(className);
		tableEntity.setClassname(StringUtils.uncapitalize(className));
		//获取长度
		//列信息
		List<Column> columsList = new ArrayList<>();
		for(Map<String, String> column : columns){
			Column columnEntity = new Column();
			columnEntity.setColumnName(column.get("columnName"));
			columnEntity.setDataType(column.get("dataType"));
			//设置字段长度
			int start=column.get("maxLength").indexOf("(");
			int end =column.get("maxLength").indexOf(")");
			if(start!=-1){
				columnEntity.setMaxLength(column.get("maxLength").substring(start+1,end));
			}else{
				columnEntity.setMaxLength("20");
			}
			columnEntity.setComments(column.get("columnComment"));
			columnEntity.setExtra(column.get("extra"));

			//列名转换成Java属性名
			String attrName = columnToJava(columnEntity.getColumnName());
			columnEntity.setAttrName(attrName);
			columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

			//列的数据类型，转换成Java类型
			String attrType = config.getString(columnEntity.getDataType(), "unknowType");
			columnEntity.setAttrType(attrType);

			//是否主键
			if("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null){
				tableEntity.setPk(columnEntity);
			}

			columsList.add(columnEntity);
		}
		tableEntity.setColumns(columsList);

		//没主键，则第一个字段为主键
		if(tableEntity.getPk() == null){
			tableEntity.setPk(tableEntity.getColumns().get(0));
		}

		//设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(prop);

		//封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableEntity.getTableName());
		map.put("comments", tableEntity.getComments());
		map.put("pk", tableEntity.getPk());
		map.put("className", tableEntity.getClassName());
		map.put("classname", tableEntity.getClassname());
		map.put("pathName", tableEntity.getClassname().toLowerCase());
		map.put("columns", tableEntity.getColumns());
		map.put("package", config.getString("package"));
		map.put("author", config.getString("author"));
		map.put("email", config.getString("email"));
		map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
		List<String> templates = getTemplates();
		for(String template : templates){
			//渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);

			try {
				//添加到zip
				zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"))));
				IOUtils.write(sw.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				throw new MyException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
			}
		}
	}


	/**
	 * 列名转换成Java属性名
	 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
	}

	/**
	 * 表名转换成Java类名
	 */
	public static String tableToJava(String tableName, String tablePrefix) {
		if(StringUtils.isNotBlank(tablePrefix)){
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName);
	}

	/**
	 * 获取配置信息
	 */
	public static Configuration getConfig(){
		try {
			return new PropertiesConfiguration("generator/generator.properties");
		} catch (ConfigurationException e) {
			throw new MyException("获取配置文件失败，", e);
		}
	}

	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, String className, String packageName){
		String packagePath = "main" + File.separator + "java" + File.separator;
		if(StringUtils.isNotBlank(packageName)){
			packagePath += packageName.replace(".", File.separator) + File.separator;
		}

		if(template.contains("Entity.java.vm")){
			return packagePath + "entity" + File.separator + className + ".java";
		}

		if(template.contains("Dao.java.vm")){
			return packagePath + "dao" + File.separator + className + "Dao.java";
		}

		if(template.contains("Mapper.xml.vm")){
			return "main" + File.separator + "resources" +  File.separator + "mapper"
					+ File.separator + className + "Mapper.xml";
		}

		if(template.contains("Service.java.vm")){
			return packagePath + "service" + File.separator + className + "Service.java";
		}

		if(template.contains("ServiceImpl.java.vm")){
			return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
		}

		if(template.contains("Controller.java.vm")){
			return packagePath + "controller" + File.separator + className + "Controller.java";
		}

		if(template.contains("list.ftl.vm")){
			return "main" + File.separator + "resources" +  File.separator + "templates"
					+ File.separator + className.toLowerCase() + File.separator + "list.ftl";
		}
		if(template.contains("add.ftl.vm")){
			return "main" + File.separator + "resources" +  File.separator + "templates"
					+ File.separator + className.toLowerCase() + File.separator + "add.ftl";
		}
		if(template.contains("edit.ftl.vm")){
			return "main" + File.separator + "resources" +  File.separator + "templates"
					+ File.separator + className.toLowerCase() + File.separator + "edit.ftl";
		}
		if(template.contains("info.ftl.vm")){
			return "main" + File.separator + "resources" +  File.separator + "templates"
					+ File.separator + className.toLowerCase() + File.separator + "info.ftl";
		}
		if(template.contains("list.js.vm")){
			return "main" + File.separator + "resources" +  File.separator + "templates"
					+ File.separator + className.toLowerCase()  +File.separator + "js"+ File.separator + "list.js";
		}
		if(template.contains("common.ftl.vm")){
			return "main" + File.separator + "resources" +  File.separator + "templates"
					+ File.separator + className.toLowerCase() + File.separator + "common.ftl";
		}
		if(template.contains("menu.sql.vm")){
			return className.toLowerCase() + "_menu.sql";
		}

		return null;
	}
}
