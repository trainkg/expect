package com.barley.config.form;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.barley.config.form.layout.RenderManager;

/**
 * @author peculiar.1@163.com
 * @version $ID: ConfigBaseController.java, V1.0.0 2019年10月20日 下午10:04:22 $
 */
@RestController
@RequestMapping(path = "/form")
public class FormController {
        
    /**
     * 获取form基本模板
     * @return
     */
    @GetMapping(path = "/template/{formId}")
    @ResponseBody
    public ResponseEntity<String> loadingFormTemplate(@PathVariable String formId) {
        RenderManager renderManaer$ = renderManaer;
        if(renderManaer$ == null) {
            renderManaer$ = new RenderManager();
        }
        return ResponseEntity
                        .ok()
                        .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
                        .body(renderManaer$.render());
    }
    
    
    
    @Autowired(required = false)
    private RenderManager renderManaer;
}
