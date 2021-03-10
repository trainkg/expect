package com.barley.pub;

/**
 * 
 * path code, 主要是针对树型结构数据设计对应节点的的路径path. 策略简单采用拼接的方式生成
 * 
 * @author peculiar.1@163.com
 * @version $ID: TreeCode.java, V1.0.0 2021年3月10日 下午4:39:05 $
 */
public abstract class PathCode implements CodeGenerater {

	@Override
	public String generate() {
		String parentCode = loadParentCode();
		String nodeCode = genereteNodeCode();
		return parentCode + nodeCode;
	}

	/**
	 * 获取节点自身编号
	 * 
	 * @return
	 */
	protected  abstract String genereteNodeCode();

	/**
	 * 
	 * 获取父节点路径
	 * 
	 * @return
	 */
	protected abstract String loadParentCode();

}
