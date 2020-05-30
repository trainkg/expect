package com.barley.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barley.config.modal.Module;
import com.barley.config.services.ModuleService;
import com.barley.config.vo.ModuleSearchVO;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

/**
 * @author peculiar.1@163.com
 * @version $ID: ConfigBaseController.java, V1.0.0 2019年10月20日 下午10:04:22 $
 */
@RestController
public class ConfigBaseController {

    /**    
     * 自动
     * 
     * @return
     */
    @RequestMapping("/config/list")
    public Map<String, Object> retriveProcessInfo(@RequestParam(defaultValue = "1") int page,
                    @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<Module> infos = seModule.findModule(new ModuleSearchVO(), page, pageSize);
        if(StringUtil.isNotEmpty(request.getParameter("note"))) {
            map.put("results", new ArrayList<Object>());
            map.put("totalCount", 0);
        }else {
            map.put("results", infos.getList());
            map.put("totalCount", infos.getTotal());
        }
        
        return map;
    }

    @Autowired
    private ModuleService seModule;
}
