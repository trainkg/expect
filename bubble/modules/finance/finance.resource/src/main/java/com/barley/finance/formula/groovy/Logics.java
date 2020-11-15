package com.barley.finance.formula.groovy;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.util.ResourceUtils;

import com.barley.finance.formula.ArgumentFormula;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

/**
 * 需要导入Groovy All  
 * 
 * @author peculiar.1@163.com
 * @version $ID: Logics.java, V1.0.0 2020年10月11日 上午11:11:55 $
 */
public class Logics extends ArgumentFormula {

	public static BigDecimal computed(Object... args) {
		return new Logics()._computed(args);
	}

	@Override
	public BigDecimal _computed(Object... args) {
		return IF(true, BigDecimal.ONE, BigDecimal.ZERO);
	}

	private static BigDecimal IF(boolean flag, BigDecimal b1, BigDecimal b2) {
		
		/*
		 * List<ScriptEngineFactory> factors = new
		 * ScriptEngineManager().getEngineFactories(); for (ScriptEngineFactory
		 * scriptEngineFactory : factors) {
		 * System.out.println(scriptEngineFactory.getLanguageName());
		 * System.out.println(scriptEngineFactory.getEngineName()); }
		 */
		/*
		 * ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");
		 * try { engine.eval("println 'hello, groovy!'"); } catch (ScriptException e) {
		 * e.printStackTrace(); }
		 */
		
		File groovyFile;
		try {
			groovyFile = ResourceUtils.getFile("classpath:com/barley/finance/formula/GroovyFormula.groovy");
		} catch (FileNotFoundException e1) {
			return null;
		}
		System.out.println(groovyFile.getAbsolutePath());
		try {
			CompilerConfiguration config = new CompilerConfiguration();
			config.setSourceEncoding("UTF-8");
			// 设置该GroovyClassLoader的父ClassLoader为当前线程的加载器(默认)
			GroovyClassLoader groovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), config);
			// 获得GroovyShell_2加载后的class
			Class<?> groovyClass = groovyClassLoader.parseClass(groovyFile);
			// 获得GroovyShell_2的实例
			GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
			// 反射调用sayHello方法得到返回值
			Object methodResult = groovyObject.invokeMethod("computed", new Object[] {new BigDecimal("1.522"), new BigDecimal("3.222")});
			if (methodResult != null) {
				System.out.println(methodResult.toString());
			}
			groovyClassLoader.close();
		} catch (Exception e) {
			
		}
		
		if (flag) {
			return b1;
		} else {
			return b2;
		}
	}
}
