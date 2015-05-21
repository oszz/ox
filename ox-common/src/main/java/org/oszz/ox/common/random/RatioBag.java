package org.oszz.ox.common.random;

import java.util.List;

/**
 * 随机包
 * @author ZZ
 *
 */
public class RatioBag<T> implements IRatioBag<T> {
	
	private List<ItemRatioBox<T>> ratioBoxes;
	private Double totalRatio;
	
	/**
	 * 构建一个随机包
	 * @param ratioBoxes 需要随机的比率箱子
	 */
	public RatioBag(List<ItemRatioBox<T>> ratioBoxes){
		this.ratioBoxes = ratioBoxes;
		this.totalRatio = RatioRandomUtils.getTotalRatio(this);
	}

	@Override
	public List<ItemRatioBox<T>> getAllRatioBoxes() {
		return ratioBoxes;
	}

	@Override
	public ItemRatioBox<T> randomOne() {
		return RatioRandomUtils.randomOne(ratioBoxes, totalRatio);
	}

	@Override
	public List<ItemRatioBox<T>> randomNonRepeatMultiple(int count) {
		return RatioRandomUtils.randomNonRepeatMultiple(ratioBoxes, totalRatio, count);
	}

	@Override
	public List<ItemRatioBox<T>> randomCanRepeatMultiple(int count) {
		return RatioRandomUtils.randomCanRepeatMultiple(ratioBoxes, totalRatio, count);
	}

	@Override
	public List<T> randomNonRepeatMultiple2Class(int count) {
		List<ItemRatioBox<T>> irBoxes = RatioRandomUtils.randomNonRepeatMultiple(ratioBoxes, totalRatio, count);
		return RatioRandomUtils.itemRatioBox2Class(irBoxes);
	}

	@Override
	public List<T> randomCanRepeatMultiple2Class(int count) {
		List<ItemRatioBox<T>> irBoxes = RatioRandomUtils.randomCanRepeatMultiple(ratioBoxes, totalRatio, count);
		return RatioRandomUtils.itemRatioBox2Class(irBoxes);
	}
	
	@Override
	public T randomOne2Class() {
		return RatioRandomUtils.randomOne(ratioBoxes, totalRatio).getObj();
	}

}
