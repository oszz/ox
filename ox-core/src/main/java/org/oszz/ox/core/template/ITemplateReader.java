package org.oszz.ox.core.template;

import java.util.Map;

public interface ITemplateReader {


	public <T extends ITemplateData> Map<Integer, ITemplateData> read(Class<T> clazz, String excelFilePath);
}
