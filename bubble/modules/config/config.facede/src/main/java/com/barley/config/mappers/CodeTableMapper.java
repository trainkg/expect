package com.barley.config.mappers;

import com.barley.config.modal.CodeTable;
import com.barley.config.vo.CodeTableCriteria;
import java.util.List;

public interface CodeTableMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_table
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long listId); 

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_table
     *
     * @mbg.generated
     */
    int insert(CodeTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_table
     *
     * @mbg.generated
     */
    int insertSelective(CodeTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_table
     *
     * @mbg.generated
     */
    List<CodeTable> selectByExample(CodeTableCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_table
     *
     * @mbg.generated
     */
    CodeTable selectByPrimaryKey(Long listId);
 
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CodeTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CodeTable record);
}