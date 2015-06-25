package org.oszz.ox.core.template.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识Excel模板类<br>
 * value是Excel的文件名，如：MyExcel.xlsx
 * @author ZZ
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExcelName {

	 public String value();
}
