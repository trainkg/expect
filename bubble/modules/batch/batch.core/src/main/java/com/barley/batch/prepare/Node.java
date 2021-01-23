package com.barley.batch.prepare;

import java.util.List;

import com.barley.batch.core.NodeData;

import lombok.Getter;
import lombok.Setter;

/**
 * Node
 * 
 * @author peculiar.1@163.com
 * @version $ID: Node.java, V1.0.0 2020年10月27日 下午8:16:03 $
 * @param <T>
 */
public class Node<V extends NodeData<T>, T> {

	private V target;

	@Getter
	@Setter
	private Node<NodeData<T>, T> parentTarget;

	@Getter
	@Setter
	private List<Node<NodeData<T>, T>> childs;
	
	/**
	 * 标记树状节点热最后一个节点
	 */
	@Getter
	@Setter
	private Node<NodeData<T>, T> lastest;
	
	

	public Node(V target) {
		this.target = target;
		
		if (target == null) {
			throw new IllegalArgumentException("target is null");
		}
	}

	public V getTarget() {
		return target;
	}

}
