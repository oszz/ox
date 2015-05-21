package org.oszz.ox.common.random;

/**
 * 比率箱子
 * @author ZZ
 *
 */
public class ItemRatioBox<T> implements IRatio {

	private T obj;//对象
	private Double ratio;
	
	/**
	 * 构建一个比率箱子
	 * @param obj 随机对象
	 * @param ratio 概率
	 */
	public ItemRatioBox(T obj, int ratio){
		this.obj = obj;
		this.ratio = ratio*1.0;
	}
	/**
	 * 构建一个比率箱子
	 * @param obj 随机对象
	 * @param ratio 概率
	 */
	public ItemRatioBox(T obj, Double ratio){
		this.obj = obj;
		this.ratio = ratio;
	}

	public T getObj() {
		return this.obj;
	}

	@Override
	public Double getRatio() {
		return this.ratio;	
	}
	
	@Override
	public String toString() {
		return "[Object" +  obj + ", ratio: " + ratio + "]";
	}
}
