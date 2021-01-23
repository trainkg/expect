package com.barley.bubble.autoconfigurate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: EnableBubbleConfig.java, V1.0.0 2021年1月20日 下午10:08:51 $
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableBubbleConfig {

}
