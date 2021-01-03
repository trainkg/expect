package com.barley.config.service;

import com.barley.config.modal.CodeTable;
import com.barley.config.service.searchvo.CodeTableSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.config.service.CodeTableBaseService create date 2020-12-31 13:35:23
 */
public interface CodeTableBaseService {
    CodeTable create(CodeTable record);

    void delete(Long keyId);

    CodeTable update(CodeTable record);

    List<CodeTable> findAll();

    CodeTable findByPrimaryKey(Long keyId);

    List<CodeTable> searchByVO(CodeTableSearchVO searchVO);

    PageInfo<CodeTable> searchByVO(CodeTableSearchVO searchVO, int page, int pageSize);
}