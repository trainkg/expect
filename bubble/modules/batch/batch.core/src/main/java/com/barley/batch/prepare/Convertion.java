package com.barley.batch.prepare;

public interface Convertion<T,S> {
	
	/**
	 * convert from S to T
	 * @param sourceObj
	 * @return
	 */
	public T convertion(S sourceObj);
}
