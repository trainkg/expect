package com.barley.system.config;

import java.util.Arrays;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 
 * 提供CORS配置
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: CORSConfig.java, V1.0.0 2021年2月1日 下午10:16:39 $
 */
public class CORSConfig implements FactoryBean<CorsConfigurationSource> {

	@Override
	public CorsConfigurationSource getObject() throws Exception {
		CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost:9090"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
	}

	@Override
	public Class<?> getObjectType() {
		return CorsConfigurationSource.class;
	}

}
