package org.oszz.ox.tools.utils;

import java.io.PrintWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * Velocity工具类，根据.vm文件生成代码
 * @author ZZ
 *
 */
public class VelocityUtils {
	
	/**
	 * 默认的字符集
	 */
	private static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 根据 Velocity的模板和Velocity的Context，将生成的代码写到文件中<br>
	 * 使用默认字符集：UTF-8
	 * @author ZZ
	 * @param template Velocity的模板
	 * @param context Velocity的Context
	 * @param outFile 写入的文件
	 * @param charsetName 写入的文件的字符集
	 */
	public static void write(Template template, VelocityContext context, String outFile) {
		write(template, context, outFile, DEFAULT_CHARSET);
	}

	/**
	 * 根据 Velocity的模板和Velocity的Context，将生成的代码写到文件中
	 * @author ZZ
	 * @param template Velocity的模板
	 * @param context Velocity的Context
	 * @param outFile 写入的文件
	 * @param charsetName 写入的文件的字符集
	 */
	public static void write(Template template, VelocityContext context, String outFile, String charsetName) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(outFile, charsetName);
			template.merge(context, writer);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(writer != null){writer.close();}
		}
	}
	
	
	/**
	 * 根据 .vm文件和Velocity的Context，将生成的代码写到文件中<br>
	 * 使用默认字符集：UTF-8
	 * @author ZZ
	 * @param vmFile .vm文件（classpath路径）
	 * @param context Velocity的Context
	 * @param outFile 写入的文件
	 */
	public static void write(String vmFile, VelocityContext context, String outFile) {
		write(vmFile, context, outFile, DEFAULT_CHARSET);
	}
	
	/**
	 * 根据 .vm文件和Velocity的Context，将生成的代码写到文件中
	 * @author ZZ
	 * @param vmFile .vm文件（classpath路径）
	 * @param context Velocity的Context
	 * @param outFile 写入的文件
	 * @param charsetName 写入的文件的字符集
	 */
	public static void write(String vmFile, VelocityContext context, String outFile, String charsetName) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		
		Template actionTpt = ve.getTemplate(vmFile, charsetName);
		write(actionTpt, context, outFile, charsetName);
	}
}
