package com.barley.party.service;

import com.barley.party.mappers.EmployeeMapper;
import com.barley.party.modal.Employee;
import com.barley.party.service.searchvo.EmployeeSearchVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.barley.mybatis.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.EmployeeBaseService create date 2020-12-27 12:09:54
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    protected EmployeeMapper entityMapper;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    public Employee create(Employee record) {
        entityMapper.insert(record);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    public void delete(Long keyId) {
        entityMapper.deleteByPrimaryKey(keyId);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    public Employee update(Employee record) {
        entityMapper.updateByPrimaryKey(record);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    public List<Employee> findAll() {
        EmployeeSearchVO searchvo = new EmployeeSearchVO();
        return entityMapper.searchByCriteria(searchvo);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    public Employee findByPrimaryKey(Long keyId) {
        Employee entity = entityMapper.selectByPrimaryKey(keyId);
        return entity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    public List<Employee> searchByVO(EmployeeSearchVO searchVO) {
        PageInfo<Employee> pageInfo = internalfindBySearchVO(searchVO, null, null);
        return pageInfo.getList();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    public PageInfo<Employee> searchByVO(EmployeeSearchVO searchVO, int page, int pageSize) {
        return internalfindBySearchVO(searchVO, page, pageSize);
    }

    protected PageInfo<Employee> internalfindBySearchVO(EmployeeSearchVO searchvo, Integer page, Integer pageSize) {
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        if (searchvo instanceof CriteriaBuilder) {
            ((CriteriaBuilder) searchvo).build();
        }
        List<Employee> list = entityMapper.searchByCriteria(searchvo);
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(list, pageSize);
        if (pagesvo != null) {
            pageInfo.setTotal(pagesvo.getTotal());
        }
        return pageInfo;
    }
}