package com.barley.system.service.base;

import com.barley.system.modal.BizModular;
import com.barley.system.service.base.searchvo.BizModularSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.BizModularBaseService create date 2021-01-03 11:09:28
 */
public interface BizModularBaseService {
    BizModular create(BizModular record);

    void delete(Integer keyId);

    BizModular update(BizModular record);

    List<BizModular> findAll();

    BizModular findByPrimaryKey(Integer keyId);

    List<BizModular> searchByVO(BizModularSearchVO searchVO);

    PageInfo<BizModular> searchByVO(BizModularSearchVO searchVO, int page, int pageSize);
}