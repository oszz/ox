package org.oszz.ox.server.module.auth.template;

import org.oszz.ox.core.template.AbstractTemplateData;
import org.oszz.ox.core.template.excel.ExcelField;

/**
 * 战斗Buff<br>
 * Auto Generator, Don't Modify .
 */
public abstract class AbstractBattleBuffTemplate extends AbstractTemplateData {

	@ExcelField
	private String buffName;
	@ExcelField
	private int buffType;
	@ExcelField
	private int continueType;
	@ExcelField
	private int buffID;
		
	/**
	 * 设置 效果名称（暂时只是注释）
	 */
	public void setBuffName(String value){
		this.buffName = value;
	}
	/**
	 * 返回 效果名称（暂时只是注释）
	 */
	public String getBuffName(){
		return this.buffName;
	}
	/**
	 * 设置 效果类型<br>
	 * 1.造成伤害<br>
	 * 2.加法攻击修正<br>
	 * 3.加法防御修正<br>
	 * 4.加法最大生命修正<br>
	 * 5.乘法攻击修正<br>
	 * 6.乘法防御修正<br>
	 * 7.乘法最大生命修正<br>
	 * 8.dot<br>
	 * 9.生命自修改<br>
	 * 10.眩晕<br>
	 * 11.强制位移<br>
	 * 12.清除buff<br>
	 * 13.效果抵抗
	 */
	public void setBuffType(int value){
		this.buffType = value;
	}
	/**
	 * 返回 效果类型<br>
	 * 1.造成伤害<br>
	 * 2.加法攻击修正<br>
	 * 3.加法防御修正<br>
	 * 4.加法最大生命修正<br>
	 * 5.乘法攻击修正<br>
	 * 6.乘法防御修正<br>
	 * 7.乘法最大生命修正<br>
	 * 8.dot<br>
	 * 9.生命自修改<br>
	 * 10.眩晕<br>
	 * 11.强制位移<br>
	 * 12.清除buff<br>
	 * 13.效果抵抗
	 */
	public int getBuffType(){
		return this.buffType;
	}
	/**
	 * 设置 效果结算类型<br>
	 * 0.瞬时结算<br>
	 * 1.获得buff
	 */
	public void setContinueType(int value){
		this.continueType = value;
	}
	/**
	 * 返回 效果结算类型<br>
	 * 0.瞬时结算<br>
	 * 1.获得buff
	 */
	public int getContinueType(){
		return this.continueType;
	}
	/**
	 * 设置 buff美术资源ID
	 */
	public void setBuffID(int value){
		this.buffID = value;
	}
	/**
	 * 返回 buff美术资源ID
	 */
	public int getBuffID(){
		return this.buffID;
	}
	
}
