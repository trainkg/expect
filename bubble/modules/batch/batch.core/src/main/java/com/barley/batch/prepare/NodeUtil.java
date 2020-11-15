package com.barley.batch.prepare;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.barley.batch.core.NodeData;

import lombok.extern.slf4j.Slf4j;

/**
 *  
 * NodeUtil
 * 
 * @author peculiar.1@163.com
 * @version $ID: NodeUtil.java, V1.0.0 2020年10月27日 下午8:59:15 $
 */
@Slf4j
public class NodeUtil<T> {
	
	/**
	 * default Null
	 */
	private T rootId;

	private List<Node<NodeData<T>, T>> allNodes;

	public NodeUtil(List<Node<NodeData<T>, T>> allNodes) {
		this.allNodes = allNodes;
	}

	public NodeUtil(List<Node<NodeData<T>, T>> allNodes, T rootId) {
		this.allNodes = allNodes;
		this.rootId = rootId;
	}

	/**
	 *    构建一个树形结构,返回单个节点 
	 *    
	 *   采用先序遍历 
	 *    
	 *     只支持单一父节点
	 * @return
	 */
	public Node<NodeData<T>, T> build() {
		log.info("start build");
		check();
		Node<NodeData<T>, T> rootNode = allNodes.stream().filter((V) -> V.getTarget().getParentNodeId() == rootId).collect(Collectors.toList()).get(0);
		List<Node<NodeData<T>, T>> childs = findChilds(rootNode,rootNode);
		forEach(rootNode, rootNode, childs);
		return rootNode;
	}
	
	/**
	 * data check
	 */
	private void check() {
		Set<T> ids = new HashSet<T>(allNodes.size());
		allNodes.stream().forEach(item -> ids.add(item.getTarget().getNodeId()));
		allNodes.stream().forEach(item -> {
			if (item.getTarget().getParentNodeId() != null && !ids.contains(item.getTarget().getParentNodeId())) {
				throw new IllegalArgumentException("parent node id not found " + item.getTarget().getParentNodeId());
			}
		});
	}
	
	private List<Node<NodeData<T>, T>> findChilds(Node<NodeData<T>, T> rootNote, Node<NodeData<T>, T> parentNode) {
		T itemId = parentNode.getTarget().getNodeId();
		List<Node<NodeData<T>, T>> childs = allNodes.stream().filter((V) -> V.getTarget().getParentNodeId() == itemId).collect(Collectors.toList());
		if(childs != null && childs.size() > 0) {
			parentNode.setChilds(childs);
		}
		return childs;
	}
	
	private void forEach(Node<NodeData<T>, T> rootNote, Node<NodeData<T>, T> parentNode, List<Node<NodeData<T>, T>> childs) {
		if(childs != null && childs.size() > 0) {
			childs.forEach(item -> {
				if(!item.getTarget().isLeaf()) {
					List<Node<NodeData<T>, T>> itemChilds = findChilds(rootNote,item);
					item.setParentTarget(parentNode);
					forEach(rootNote, item, itemChilds);
				}else {
					rootNote.setLastest(item);
				}
			});
		}
    }
	
}