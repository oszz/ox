package org.oszz.ox.core.template.excel;

/**
 * excel的配置
 * @author ZZ
 *
 */
public class ExcelCoifig {
	
	private int dataSheetIndex;//数据sheet的索引位置

	private int typeRowIndex;//类型所在的行索引
	private int nameRowIndex;//名字所在的行索引
	private int descRowIndex;//描述所在的行索引
	private int dataStartRowIndex;//数据开始 的行索引
	
	public ExcelCoifig(int dataSheetIndex, int typeRowIndex, int nameRowIndex,
			int descRowIndex, int dataStartRowIndex){
		this.dataSheetIndex = dataSheetIndex;
		this.typeRowIndex = typeRowIndex;
		this.nameRowIndex = nameRowIndex;
		this.descRowIndex = descRowIndex;
		this.dataStartRowIndex = dataStartRowIndex;
	}
	
	public static ExcelCoifig newDefault(){
		return new ExcelCoifig(ExcelDefaultCoifig.DATA_SHEET.getIndex(), 
				ExcelDefaultCoifig.TYPE_ROW.getIndex(), ExcelDefaultCoifig.NAME_ROW.getIndex(),
				ExcelDefaultCoifig.DESC_ROW.getIndex(), ExcelDefaultCoifig.DATA_START_ROW.getIndex());
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
}
