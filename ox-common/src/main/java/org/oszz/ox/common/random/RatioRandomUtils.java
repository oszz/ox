package org.oszz.ox.common.random;

import java.util.ArrayList;
import java.util.List;

/**
 * 随机工具
 * @author ZZ
 *
 */
public class RatioRandomUtils {

	/**
	 * 获得总概率
	 * @author ZZ
	 * @param ratioBag
	 * @return
	 */
	public static <T> Double getTotalRatio(IRatioBag<T> ratioBag){
		List<ItemRatioBox<T>> boxes = ratioBag.getAllRatioBoxes();
		return getTotalRatio(boxes);
	}
	
	/**
	 * 获得总概率
	 * @author ZZ
	 * @param boxes
	 * @return
	 */
	public static <T> Double getTotalRatio(List<ItemRatioBox<T>> boxes){
		Double tatalRatio = 0.0;
		if(boxes != null && !boxes.isEmpty()){
			for(ItemRatioBox<T> box : boxes){
				tatalRatio += box.getRatio();
			}
		}
		return tatalRatio;
	}
	
	/**
	 * 随机一个对象
	 * @author ZZ
	 * @param ratioBoxes 随机的box
	 * @param totalRatio 总概率
	 * @return 返回随机到的一个对象
	 */
	public static <T> ItemRatioBox<T> randomOne(List<ItemRatioBox<T>> ratioBoxes, Double totalRatio){
		Double randomNum = Math.random()*totalRatio;
		ItemRatioBox<T> randomBox = null;
		if(ratioBoxes != null && !ratioBoxes.isEmpty()){
			for(ItemRatioBox<T> box : ratioBoxes){
				Double ratio = box.getRatio();
				if(isInBlock(ratio, randomNum)){
					randomBox = box;
					return box;
				}else{
					randomNum -= ratio;
				}
			}
		}
		return randomBox;
	}
	
	/**
	 * 是否命中
	 * @author ZZ
	 * @param ratio 比率
	 * @param totalRatio 总比率
	 * @return 如果命中，则返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public static boolean isHit(double ratio, double totalRatio){
		Double randomNum = Math.random()*totalRatio;
		return isInBlock(ratio, randomNum);
	}
	
	/**
	 * 是否在该区间内
	 * @author ZZ
	 * @param ratio 一个概率
	 * @param randomNum 随机数
	 * @return 如果在该区间内，则返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	private static boolean isInBlock(double ratio, double randomNum){
		if(randomNum <= ratio){
			return true;
		}
		return false;
	}
	
	/**
	 * 随机多个且不重复的
	 * @author ZZ
	 * @param ratioBoxes 随机的box
	 * @param totalRatio 总概率
	 * @param count 随机个数
	 * @return 返回随机多个且不重复的
	 */
	public static <T> List<ItemRatioBox<T>> randomNonRepeatMultiple(List<ItemRatioBox<T>> ratioBoxes, Double totalRatio, int count){
		List<ItemRatioBox<T>> dicRatioBoxes =  new ArrayList<ItemRatioBox<T>>() ;//复制一份，因为后面有删除，会影响原list
		dicRatioBoxes.addAll(ratioBoxes);
		List<ItemRatioBox<T>> rBoxes = new ArrayList<ItemRatioBox<T>>();//返回的list
		int size = dicRatioBoxes.size();
		if(size < count){//如果要求的个数，大于随机包的长度，则最多是将随机包所有的数据拿出去
			count = size;
		}
		for(int i=0;i<count;i++){
			totalRatio = getTotalRatio(dicRatioBoxes);//每次都得计算一次总概率
			ItemRatioBox<T> ratioBox = randomOne(dicRatioBoxes, totalRatio);
			dicRatioBoxes.remove(ratioBox);// 移除已经随机过的
			rBoxes.add(ratioBox);
		}
		return rBoxes;
	}
	
	/**
	 * 随机多个且可重复的
	 * @author ZZ
	 * @param ratioBoxes 随机的box
	 * @param totalRatio 总概率
	 * @param count 随机个数
	 * @return 返回随机多个且可重复的
	 */
	public static <T> List<ItemRatioBox<T>> randomCanRepeatMultiple(List<ItemRatioBox<T>> ratioBoxes, Double totalRatio, int count){
		List<ItemRatioBox<T>> rBoxes = new ArrayList<ItemRatioBox<T>>();
		for(int i=0;i<count;i++){
			ItemRatioBox<T> ratioBox = randomOne(ratioBoxes, totalRatio);
			rBoxes.add(ratioBox);
		}
		return rBoxes;
	}
	
	/**
	 * 将box列表装成box中的对象列表
	 * @author ZZ
	 * @param itemRatioBoxes 随机的包
	 * @return 返回box中的对象列表
	 */
	protected static <T> List<T> itemRatioBox2Class(List<ItemRatioBox<T>> itemRatioBoxes){
		List<T> list = null;
		if(itemRatioBoxes != null && !itemRatioBoxes.isEmpty()){
			list = new ArrayList<T>();
			for(ItemRatioBox<T> irBox : itemRatioBoxes){
				list.add((T)irBox.getObj());
			}
		}
		return list;
	}
	
	
}
