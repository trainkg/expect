package com.barley.batch.core.dayend;

import java.util.List;
import java.util.Map;

import com.barley.batch.core.NodeData;
import com.barley.batch.prepare.Node;

import lombok.Getter;
import lombok.Setter;

/**
 * 记录dayend job执行上下文
 * 
 * @author peculiar.1@163.com
 * @version $ID: DayendJobExecutor.java, V1.0.0 2020年11月7日 下午6:59:36 $
 */
public class DayendJobExecution {
	
	
	@Getter
	@Setter
	private Node<NodeData<Long>, Long> rootNote;
	
	/**
	 * 执行地图 
	 */
	private Map<Long,List<Node<NodeData<Long>, Long>>> executeMap;
	
	
}
