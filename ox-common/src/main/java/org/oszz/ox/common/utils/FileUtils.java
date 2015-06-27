package org.oszz.ox.common.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件路径工具
 * @author ZZ
 *
 */
public class FileUtils {

	/**
	 * 获得绝对路径用于读取该路径下的文件.<br>
	 * 如果路径是绝对路径，那么就直接返回，如果不是，就去ClassPath找该路径.
	 * @author ZZ
	 * @param inputPath 文件目录路径
	 * @return 返回绝对路径
	 */
	public static String getAbsolutePathForRead(String inputPath){
		File inputDir = new File(inputPath);
		if(!inputDir.isDirectory()){//是否是一个有效的目录
			//不是有效的目录，就认为是classPath目录
			URL url = Thread.currentThread().getContextClassLoader().getResource(inputPath);
			inputDir = new File(url.getPath());
		}
		return inputDir.getAbsolutePath();
	}
	
	/**
	 * 获得绝对路径用于在该路径下的写文件<br>
	 * 如果路径是绝对路径，判断是否存在，如果不存在，则创建该目录<br>
	 * 如果不是绝对路径，就在项目路径下创建该目录
	 * @author ZZ
	 * @param outPath 文件目录路径
	 * @return 返回可用的绝对路径
	 */
	public static String getAbsolutePathForWrite(String outPath){
		String userDir = SystemProperty.USRE_DIR.getValue();
		File outDir = new File(outPath);
		if(outDir.isAbsolute()){
			outPath = outDir.getAbsolutePath();
		}else{
			outPath = userDir + "/" + outPath;
			outDir = new File(outPath);
		}
		if(!outDir.exists()){
			outDir.mkdirs();
		}
		return outPath;
	}
	
	/**
	 * 如果绝对路径存在就返回.<br>
	 * 如果不存在，就创建，保证该路径可用.
	 * @author ZZ
	 * @param path 绝对路径
	 * @return 返回绝对路径
	 */
	public static String getDirIfExists(String path){
		File outDir = new File(path);
		if(!outDir.exists()){
			outDir.mkdirs();
		}
		return outDir.getAbsolutePath();
	}
	
	/**
	 * 返回某个文件目录下的所有文件<br>
	 * 1.不包含子文件目录<br>
	 * 2.不包含子文件目录内的文件
	 * @author ZZ
	 * @param dirPath 文件目录
	 * @return 返回某个文件目录下的所有文件
	 */
	public File[] getFiles(String dirPath){
		return getFiles(dirPath, "");
	}
	
	/**
	 * 返回某个文件目录下的所有是suffixName后缀名的文件<br>
	 * 1.不包含子文件目录<br>
	 * 2.不包含子文件目录内的文件
	 * @author ZZ
	 * @param dirPath 文件目录
	 * @param suffixName 文件的后缀名
	 * @return 返回某个文件目录下的所有是suffixName后缀名的文件
	 */
	public File[] getFiles(String dirPath, String suffixName){
		File dirFile = new File(dirPath);
		List<File> allFiles = new ArrayList<File>();
		if(dirFile.exists()){
			File[] files = dirFile.listFiles();
			
			suffixName = suffixName.trim();
			boolean suffixNameIsBlank = true;
			if(suffixName != null && !"".equals(suffixName)){
				suffixNameIsBlank = false;
			}
			for(File file : files){
				if(file.isFile()){
					if(suffixNameIsBlank){//如果参数suffixName是空的
						allFiles.add(file);
					}else{//不是空白，则判断后缀是否一致
						String fileName = file.getName();
						int lastPointIndex = fileName.lastIndexOf(".");
						if(lastPointIndex > 0){
							//文件的后缀名
							String fileSuffixName = fileName.substring(lastPointIndex);
							if(fileSuffixName.equalsIgnoreCase(suffixName)){
								allFiles.add(file);
							}
						}
					}
				}
			}
		}
		return allFiles.toArray(new File[0]);
	}
	
	/**
	 * 返回去掉后缀名的文件名<br>
	 * 如果没有后缀名直接返回
	 * @author ZZ
	 * @param fileSimpleName 带后缀名的文件名
	 * @return 返回去掉后缀名的文件名
	 */
	public static String removeSuffixName(String fileSimpleName){
		int lastPointIndex = fileSimpleName.lastIndexOf(".");
		String name = fileSimpleName;
		if(lastPointIndex > 0){
			name = fileSimpleName.substring(0, lastPointIndex);
		}
		return name;
	}
	
}
