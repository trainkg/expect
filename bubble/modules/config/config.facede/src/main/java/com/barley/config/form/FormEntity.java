package com.barley.config.form;

import java.util.List;

import com.barley.config.modal.Form;
import com.barley.config.modal.FormFeild;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peculiar.1@163.com
 * @version $ID: FormEntity.java, V1.0.0 2020年5月22日 下午8:54:32 $
 */
public class FormEntity {
    
    /**
     * 基础Form定义 
     */
    @Getter
    @Setter
    private Form formBase;
    
    /**
     * 列表包含 
     */
    @Getter
    @Setter
    private List<FormFeild> formFeilds;
    
}
