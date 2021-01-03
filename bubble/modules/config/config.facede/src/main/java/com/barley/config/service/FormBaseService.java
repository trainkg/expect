package com.barley.config.service;

import com.barley.config.modal.Form;
import com.barley.config.service.searchvo.FormSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.config.service.FormBaseService create date 2020-12-31 13:35:23
 */
public interface FormBaseService {
    Form create(Form record);

    void delete(String keyId);

    Form update(Form record);

    List<Form> findAll();

    Form findByPrimaryKey(String keyId);

    List<Form> searchByVO(FormSearchVO searchVO);

    PageInfo<Form> searchByVO(FormSearchVO searchVO, int page, int pageSize);
}