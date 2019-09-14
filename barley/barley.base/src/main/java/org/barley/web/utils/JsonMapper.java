/*
 * 文件名称:          LogInfoFetch.java
 * 版权所有@ 2013-2014 无锡城市云计算中心有限公司，保留所有权利
 * 编译器:           JDK1.7.0_25
 * 时间:             上午10:04:52
 */
package org.barley.web.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * <p>
 *  应用自 SPRINGSIDE.4
 * <p>
 * @版本:       CloudView V1.8
 * <p>
 * <p>
 * @日期:       2013-10-30
 * <p>
 * @负责人:     zhuyy
 * <p>
 * @负责小组:   commons
 * <p>
 * <p>
 */
public class JsonMapper
{

    /**
     * LOGGER
     */
    private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

    /**
     * 对象映射器 
     */
    private ObjectMapper mapper;

    /**
     * C
     */
    public JsonMapper()
    {
        this(null);
    }

    /**
     * C
     * @param include include
     */
    public JsonMapper(Include include)
    {
        mapper = new ObjectMapper();
        // 设置输出时包含属性的风格
        if (include != null)
        {
            mapper.setSerializationInclusion(include);
        }
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
     * @return JsonMapper
     */
    public static JsonMapper nonEmptyMapper()
    {
        return new JsonMapper(Include.NON_EMPTY);
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
     * @return jsonmapper
     */
    public static JsonMapper nonDefaultMapper()
    {
        return new JsonMapper(Include.NON_DEFAULT);
    }

    /**
     * Object可以是POJO，也可以是Collection或数组。
     * 如果对象为Null, 返回"null".
     * 如果集合为空集合, 返回"[]".
     * @param object Object
     * @return String
     */
    public String toJson(Object object)
    {
        try
        {
            return mapper.writeValueAsString(object);
        }
        catch(IOException e)
        {
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }

    /**
     * 反序列化POJO或简单Collection如List<String>.
     * 
     * 如果JSON字符串为Null或"null"字符串, 返回Null.
     * 如果JSON字符串为"[]", 返回空集合.
     * 
     * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String, JavaType)
     * 
     * @param jsonString JSON STRING
     * @param clazz CLASS
     * @param <T> T
     * @see #fromJson(String, JavaType)
     * @return CLAZZ INSTANCE
     */
    public <T extends Object> T fromJson(String jsonString, Class<T> clazz)
    {
        if (StringUtils.isEmpty(jsonString))
        {
            return null;
        }

        try
        {
            return mapper.readValue(jsonString, clazz);
        }
        catch(IOException e)
        {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 反序列化复杂Collection如List<Bean>, 先使用createCollectionType()或contructMapType()构造类型, 然后调用本函数.
     * @param jsonString json string
     * @param javaType java type
     * @param <T> T
     * @see #createCollectionType(Class, Class...)
     * @return T 
     */
    public <T extends Object> T fromJson(String jsonString, JavaType javaType)
    {
        if (StringUtils.isEmpty(jsonString))
        {
            return null;
        }

        try
        {
            return mapper.readValue(jsonString, javaType);
        }
        catch(IOException e)
        {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 构造Collection类型.
     * @param collectionClass collections
     * @param elementClass 实体类型定义
     * @return {@link JavaType}
     */
    public JavaType contructCollectionType(Class< ? extends Collection< ? >> collectionClass,
        Class< ? > elementClass)
    {
        return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

    /**
     * 构造Map类型.
     * @param mapClass  mapClass
     * @param keyClass  keyClass
     * @param valueClass  valueClass
     * @return JavaType
     */
    @ SuppressWarnings({"rawtypes"})
    public JavaType contructMapType(Class< ? extends Map> mapClass, Class< ? > keyClass,
        Class< ? > valueClass)
    {
        return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
    }

    /**
     * 當JSON裡只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
     * @param jsonString json 
     * @param object object
     * @param <T> T
     * @return T
     */
    public <T extends Object>T update(String jsonString, T object)
    {
        try
        {
            return mapper.readerForUpdating(object).readValue(jsonString);
        }
        catch(JsonProcessingException e)
        {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        }
        catch(IOException e)
        {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        }
        return null;
    }

    /**
     * 輸出JSONP格式數據.
     * @param functionName name
     * @param object Object
     * @return String 
     */
    public String toJsonP(String functionName, Object object)
    {
        return toJson(new JSONPObject(functionName, object));
    }

    /**
     * 設定是否使用Enum的toString函數來讀寫Enum,
     * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
     * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
     */
    public void enableEnumUseToString()
    {
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    /**
     * 取出Mapper做进一步的设置或使用其他序列化API.
     * @return {@link ObjectMapper}
     */
    public ObjectMapper getMapper()
    {
        return mapper;
    }
}
