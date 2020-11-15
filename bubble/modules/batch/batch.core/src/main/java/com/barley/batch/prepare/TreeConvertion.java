package com.barley.batch.prepare;

import java.util.ArrayList;
import java.util.List;

import com.barley.batch.core.CornJob;
import com.barley.batch.core.NodeData;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 提供一个树形转换器
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: TreeConvertion.java, V1.0.0 2020年10月27日 下午8:24:30 $
 */
@Slf4j
public class TreeConvertion implements Convertion<Node<NodeData<Long>, Long>, List<CornJob>> {

	@Override
	public Node<NodeData<Long>, Long> convertion(List<CornJob> sourceObj) {
		if (log.isDebugEnabled()) {
			log.debug("convertion start");
		}

		List<Node<NodeData<Long>, Long>> nodes = new ArrayList<Node<NodeData<Long>, Long>>(sourceObj.size());

		for (CornJob job : sourceObj) {
			Node<NodeData<Long>, Long> node = new Node<NodeData<Long>, Long>(job);
			nodes.add(node);
		}

		return new NodeUtil<Long>(nodes, null).build();
	}

}
