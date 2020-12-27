package com.barley.party.modal;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.barley.bo.AutomaticEntityImpl;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_user
 */
public class User extends AutomaticEntityImpl implements Serializable {
    /**
     * Database Column Remarks:
     *   系统用户
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     * Database Column Remarks:
     *   帐户激活时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.activation_time
     *
     * @mbg.generated
     */
    private LocalDateTime activationTime;

    /**
     * Database Column Remarks:
     *   系统时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.application_time
     *
     * @mbg.generated
     */
    private LocalDate applicationTime;

    /**
     * Database Column Remarks:
     *   邮箱地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.email
     *
     * @mbg.generated
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.first_login_time
     *
     * @mbg.generated
     */
    private LocalDateTime firstLoginTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.last_login_time
     *
     * @mbg.generated
     */
    private LocalDateTime lastLoginTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.login_name
     *
     * @mbg.generated
     */
    private String loginName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.login_times
     *
     * @mbg.generated
     */
    private Integer loginTimes;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.source_id
     *
     * @mbg.generated
     */
    private String sourceId;

    /**
     * Database Column Remarks:
     *   名称拼写
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.spell
     *
     * @mbg.generated
     */
    private String spell;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.user_status
     *
     * @mbg.generated
     */
    private Integer userStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.user_type
     *
     * @mbg.generated
     */
    private Integer userType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_user
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.id
     *
     * @return the value of t_user.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.id
     *
     * @param id the value for t_user.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.activation_time
     *
     * @return the value of t_user.activation_time
     *
     * @mbg.generated
     */
    public LocalDateTime getActivationTime() {
        return activationTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.activation_time
     *
     * @param activationTime the value for t_user.activation_time
     *
     * @mbg.generated
     */
    public void setActivationTime(LocalDateTime activationTime) {
        this.activationTime = activationTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.application_time
     *
     * @return the value of t_user.application_time
     *
     * @mbg.generated
     */
    public LocalDate getApplicationTime() {
        return applicationTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.application_time
     *
     * @param applicationTime the value for t_user.application_time
     *
     * @mbg.generated
     */
    public void setApplicationTime(LocalDate applicationTime) {
        this.applicationTime = applicationTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.email
     *
     * @return the value of t_user.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.email
     *
     * @param email the value for t_user.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.first_login_time
     *
     * @return the value of t_user.first_login_time
     *
     * @mbg.generated
     */
    public LocalDateTime getFirstLoginTime() {
        return firstLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.first_login_time
     *
     * @param firstLoginTime the value for t_user.first_login_time
     *
     * @mbg.generated
     */
    public void setFirstLoginTime(LocalDateTime firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.last_login_time
     *
     * @return the value of t_user.last_login_time
     *
     * @mbg.generated
     */
    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.last_login_time
     *
     * @param lastLoginTime the value for t_user.last_login_time
     *
     * @mbg.generated
     */
    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.login_name
     *
     * @return the value of t_user.login_name
     *
     * @mbg.generated
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.login_name
     *
     * @param loginName the value for t_user.login_name
     *
     * @mbg.generated
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.login_times
     *
     * @return the value of t_user.login_times
     *
     * @mbg.generated
     */
    public Integer getLoginTimes() {
        return loginTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.login_times
     *
     * @param loginTimes the value for t_user.login_times
     *
     * @mbg.generated
     */
    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.password
     *
     * @return the value of t_user.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.password
     *
     * @param password the value for t_user.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.source_id
     *
     * @return the value of t_user.source_id
     *
     * @mbg.generated
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.source_id
     *
     * @param sourceId the value for t_user.source_id
     *
     * @mbg.generated
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.spell
     *
     * @return the value of t_user.spell
     *
     * @mbg.generated
     */
    public String getSpell() {
        return spell;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.spell
     *
     * @param spell the value for t_user.spell
     *
     * @mbg.generated
     */
    public void setSpell(String spell) {
        this.spell = spell == null ? null : spell.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.user_status
     *
     * @return the value of t_user.user_status
     *
     * @mbg.generated
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.user_status
     *
     * @param userStatus the value for t_user.user_status
     *
     * @mbg.generated
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.user_type
     *
     * @return the value of t_user.user_type
     *
     * @mbg.generated
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.user_type
     *
     * @param userType the value for t_user.user_type
     *
     * @mbg.generated
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}