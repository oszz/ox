package org.oszz.ox.common.tuple;

/**
 * 二维元组
 * @author ZZ
 *
 */
public class TwoTuple<A, B> {

	private A first;
	private B second;
	
	public TwoTuple(A first, B second){
		this.first = first;
		this.second = second;
	}
	
	public A getFirst() {
		return first;
	}
	public B getSecond() {
		return second;
	}
	
	@Override
	public String toString() {
		return "first: [" + this.first + "] - second: [" + second + "]";
	}
	
	
}
