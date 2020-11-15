package com.barley.batch.core;

/**
 * Node 节点数据
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: NodeData.java, V1.0.0 2020年10月27日 下午8:54:43 $
 */
public interface NodeData<T> {
	public T getNodeId();
	public T getParentNodeId();
	public boolean isLeaf();
}
