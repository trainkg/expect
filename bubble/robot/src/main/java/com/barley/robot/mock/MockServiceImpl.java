package com.barley.robot.mock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.mybatis.generator.api.IntrospectedTable;

import com.barley.robot.mybatis.BubbleMybatisPlugin;

/**
 * 
 * 负责生成一些测试数据
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: MockService.java, V1.0.0 2021年3月8日 下午9:14:53 $
 */
public class MockServiceImpl implements MockService {

	private IntrospectedTable introspectedTable;

	public MockServiceImpl(IntrospectedTable introspectedTable) {
		this.introspectedTable = introspectedTable;
	}

	/**
	 * 生成菜单
	 */
	public void mockModule() {
		// 创建菜单 -> 打开表单页面
		// 查询 & 查看页面 -> 数据列表 & 数据详细页面

		String createModuLe = ""; // TODO
		String createModuLeUrl = ""; // TODO

		String searchModuLe = ""; // TODO
		String searchModuLeUrl = ""; // TODO

		createModule(createModuLe, createModuLeUrl);
		createModule(searchModuLe, searchModuLeUrl);
	}

	/**
	 * 生成module数据
	 * 
	 * @param moduleName
	 * @param url
	 * @return
	 */
	private Module createModule(String moduleName, String url) {
		Connection connection = null;
		try {
			connection = BubbleMybatisPlugin.getConnection(introspectedTable.getContext());
			QueryRunner queryRunner = new QueryRunner();
			
			Module module = new Module();
			// 为表添加唯一约束， 存在冲突数据时候不执行插入
			// 自动增长
			String sql = "insert IGNORE into t_module(parent_id,name,uri) values (7,'" + moduleName + "','" + url + "');";
			module = queryRunner.insert(sql, new ResultSetHandler<Module>() {
				@Override
				public Module handle(ResultSet rs) throws SQLException {
					Module module = new Module();
					while (rs.next()) {
					}
					return module;
				}
			});
			return module;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			BubbleMybatisPlugin.closeConnection(connection);
		}
	}

}
