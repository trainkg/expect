package com.barley.party.mappers;

import com.barley.party.modal.UserType;
import com.barley.party.vo.UserTypeCriteria;
import java.util.List;

public interface UserTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Short listId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbg.generated
     */
    int insert(UserType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbg.generated
     */
    int insertSelective(UserType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbg.generated
     */
    List<UserType> selectByExample(UserTypeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbg.generated
     */
    UserType selectByPrimaryKey(Short listId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserType record);
}