package com.barley.system.service.auth;

import com.barley.system.modal.Group;
import com.barley.system.service.auth.searchvo.GroupSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.auth.GroupBaseService create date 2021-01-03 11:09:28
 */
public interface GroupBaseService {
    Group create(Group record);

    void delete(Integer keyId);

    Group update(Group record);

    List<Group> findAll();

    Group findByPrimaryKey(Integer keyId);

    List<Group> searchByVO(GroupSearchVO searchVO);

    PageInfo<Group> searchByVO(GroupSearchVO searchVO, int page, int pageSize);
}