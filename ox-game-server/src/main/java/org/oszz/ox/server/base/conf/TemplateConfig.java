package org.oszz.ox.server.base.conf;

import org.oszz.ox.core.template.ITemplateConfig;


/**
 * excel的配置
 * @author ZZ
 *
 */
public class TemplateConfig implements ITemplateConfig{
	
	private String dirPath;//excel文件目录
	
	private int dataSheetIndex;//数据sheet的索引位置

	private int typeRowIndex;//类型所在的行索引
	private int nameRowIndex;//名字所在的行索引
	private int descRowIndex;//描述所在的行索引
	private int dataStartRowIndex;//数据开始 的行索引
	
	public TemplateConfig () {
	}
	public TemplateConfig(int dataSheetIndex, int typeRowIndex, int nameRowIndex,
			int descRowIndex, int dataStartRowIndex){
		this.dataSheetIndex = dataSheetIndex;
		this.typeRowIndex = typeRowIndex;
		this.nameRowIndex = nameRowIndex;
		this.descRowIndex = descRowIndex;
		this.dataStartRowIndex = dataStartRowIndex;
	}
	
	public static TemplateConfig newDefault(){
		return new TemplateConfig(TemplateDefaultCoifig.DATA_SHEET.getIndex(), 
				TemplateDefaultCoifig.TYPE_ROW.getIndex(), TemplateDefaultCoifig.NAME_ROW.getIndex(),
				TemplateDefaultCoifig.DESC_ROW.getIndex(), TemplateDefaultCoifig.DATA_START_ROW.getIndex());
	}
	
	public int getDataSheetIndex() {
		return dataSheetIndex;
	}
	public void setDataSheetIndex(int dataSheetIndex) {
		this.dataSheetIndex = dataSheetIndex;
	}
	public int getTypeRowIndex() {
		return typeRowIndex;
	}
	public void setTypeRowIndex(int typeRowIndex) {
		this.typeRowIndex = typeRowIndex;
	}
	public int getNameRowIndex() {
		return nameRowIndex;
	}
	public void setNameRowIndex(int nameRowIndex) {
		this.nameRowIndex = nameRowIndex;
	}
	public int getDescRowIndex() {
		return descRowIndex;
	}
	public void setDescRowIndex(int descRowIndex) {
		this.descRowIndex = descRowIndex;
	}
	public int getDataStartRowIndex() {
		return dataStartRowIndex;
	}
	public void setDataStartRowIndex(int dataStartRowIndex) {
		this.dataStartRowIndex = dataStartRowIndex;
	}

	public String getDirPath() {
		return dirPath;
	}
	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}
}
