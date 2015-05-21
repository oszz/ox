package org.oszz.ox.common.tuple;

/**
 * 三元组
 * @author ZZ
 *
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
	
	private C third;

	public ThreeTuple(A first, B second, C third) {
		super(first, second);
		this.third = third;
	}

	public C getThird() {
		return third;
	}

	public void setThird(C third) {
		this.third = third;
	}
	
	

}
