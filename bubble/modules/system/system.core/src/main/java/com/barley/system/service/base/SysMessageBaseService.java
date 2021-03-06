package com.barley.system.service.base;

import com.barley.system.modal.SysMessage;
import com.barley.system.service.base.searchvo.SysMessageSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.SysMessageBaseService
 */
public interface SysMessageBaseService {
    SysMessage create(SysMessage record);

    void delete(Integer keyId);

    SysMessage update(SysMessage record);

    List<SysMessage> findAll();

    SysMessage findByPrimaryKey(Integer keyId);

    List<SysMessage> searchByVO(SysMessageSearchVO searchVO);

    PageInfo<SysMessage> searchByVO(SysMessageSearchVO searchVO, int page, int pageSize);
}