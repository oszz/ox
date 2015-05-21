package org.oszz.ox.common.random;

/**
 * 命中随机包
 * @author ZZ
 *
 */
public class HitRatio implements IRatio {
	
	private double ratio;//概率
	private double totalRatio;//总概率
	
	/**
	 * 构建一个命中随机包
	 * @param ratio 相对概率
	 * @param totalRatio 总概率
	 */
	public HitRatio(double ratio, double totalRatio){
		this.ratio = ratio;
		this.totalRatio = totalRatio;
	}
	/**
	 * 构建一个命中随机
	 * @param ratio 相对概率
	 * @param totalRatio 总概率
	 */
	public HitRatio(int ratio, int totalRatio){
		this(ratio*1.0, totalRatio*1.0);
	}
	
	/**
	 * 是否命中
	 * @author ZZ
	 * @return
	 */
	public boolean isHit(){
		return RatioRandomUtils.isHit(ratio, totalRatio);
	}

	@Override
	public Double getRatio() {
		return this.ratio;
	}

}
