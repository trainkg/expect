package com.barley.system.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoginChannelCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    public LoginChannelCriteria() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andListIdIsNull() {
            addCriterion("`list_id` is null");
            return (Criteria) this;
        }

        public Criteria andListIdIsNotNull() {
            addCriterion("`list_id` is not null");
            return (Criteria) this;
        }

        public Criteria andListIdEqualTo(Integer value) {
            addCriterion("`list_id` =", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdNotEqualTo(Integer value) {
            addCriterion("`list_id` <>", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdGreaterThan(Integer value) {
            addCriterion("`list_id` >", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`list_id` >=", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdLessThan(Integer value) {
            addCriterion("`list_id` <", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdLessThanOrEqualTo(Integer value) {
            addCriterion("`list_id` <=", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdIn(List<Integer> values) {
            addCriterion("`list_id` in", values, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdNotIn(List<Integer> values) {
            addCriterion("`list_id` not in", values, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdBetween(Integer value1, Integer value2) {
            addCriterion("`list_id` between", value1, value2, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`list_id` not between", value1, value2, "listId");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("`code` is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("`code` is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("`code` =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("`code` <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("`code` >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("`code` >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("`code` <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("`code` <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("`code` like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("`code` not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("`code` in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("`code` not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("`code` between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("`code` not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andDescibeIsNull() {
            addCriterion("`descibe` is null");
            return (Criteria) this;
        }

        public Criteria andDescibeIsNotNull() {
            addCriterion("`descibe` is not null");
            return (Criteria) this;
        }

        public Criteria andDescibeEqualTo(String value) {
            addCriterion("`descibe` =", value, "descibe");
            return (Criteria) this;
        }

        public Criteria andDescibeNotEqualTo(String value) {
            addCriterion("`descibe` <>", value, "descibe");
            return (Criteria) this;
        }

        public Criteria andDescibeGreaterThan(String value) {
            addCriterion("`descibe` >", value, "descibe");
            return (Criteria) this;
        }

        public Criteria andDescibeGreaterThanOrEqualTo(String value) {
            addCriterion("`descibe` >=", value, "descibe");
            return (Criteria) this;
        }

        public Criteria andDescibeLessThan(String value) {
            addCriterion("`descibe` <", value, "descibe");
            return (Criteria) this;
        }

        public Criteria andDescibeLessThanOrEqualTo(String value) {
            addCriterion("`descibe` <=", value, "descibe");
            return (Criteria) this;
        }

        public Criteria andDescibeLike(String value) {
            addCriterion("`descibe` like", value, "descibe");
            return (Criteria) this;
        }

        public Criteria andDescibeNotLike(String value) {
            addCriterion("`descibe` not like", value, "descibe");
            return (Criteria) this;
        }

        public Criteria andDescibeIn(List<String> values) {
            addCriterion("`descibe` in", values, "descibe");
            return (Criteria) this;
        }

        public Criteria andDescibeNotIn(List<String> values) {
            addCriterion("`descibe` not in", values, "descibe");
            return (Criteria) this;
        }

        public Criteria andDescibeBetween(String value1, String value2) {
            addCriterion("`descibe` between", value1, value2, "descibe");
            return (Criteria) this;
        }

        public Criteria andDescibeNotBetween(String value1, String value2) {
            addCriterion("`descibe` not between", value1, value2, "descibe");
            return (Criteria) this;
        }

        public Criteria andSecretIsNull() {
            addCriterion("`secret` is null");
            return (Criteria) this;
        }

        public Criteria andSecretIsNotNull() {
            addCriterion("`secret` is not null");
            return (Criteria) this;
        }

        public Criteria andSecretEqualTo(String value) {
            addCriterion("`secret` =", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotEqualTo(String value) {
            addCriterion("`secret` <>", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretGreaterThan(String value) {
            addCriterion("`secret` >", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretGreaterThanOrEqualTo(String value) {
            addCriterion("`secret` >=", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLessThan(String value) {
            addCriterion("`secret` <", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLessThanOrEqualTo(String value) {
            addCriterion("`secret` <=", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLike(String value) {
            addCriterion("`secret` like", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotLike(String value) {
            addCriterion("`secret` not like", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretIn(List<String> values) {
            addCriterion("`secret` in", values, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotIn(List<String> values) {
            addCriterion("`secret` not in", values, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretBetween(String value1, String value2) {
            addCriterion("`secret` between", value1, value2, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotBetween(String value1, String value2) {
            addCriterion("`secret` not between", value1, value2, "secret");
            return (Criteria) this;
        }

        public Criteria andInsertByIsNull() {
            addCriterion("`insert_by` is null");
            return (Criteria) this;
        }

        public Criteria andInsertByIsNotNull() {
            addCriterion("`insert_by` is not null");
            return (Criteria) this;
        }

        public Criteria andInsertByEqualTo(Long value) {
            addCriterion("`insert_by` =", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByNotEqualTo(Long value) {
            addCriterion("`insert_by` <>", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByGreaterThan(Long value) {
            addCriterion("`insert_by` >", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByGreaterThanOrEqualTo(Long value) {
            addCriterion("`insert_by` >=", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByLessThan(Long value) {
            addCriterion("`insert_by` <", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByLessThanOrEqualTo(Long value) {
            addCriterion("`insert_by` <=", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByIn(List<Long> values) {
            addCriterion("`insert_by` in", values, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByNotIn(List<Long> values) {
            addCriterion("`insert_by` not in", values, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByBetween(Long value1, Long value2) {
            addCriterion("`insert_by` between", value1, value2, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByNotBetween(Long value1, Long value2) {
            addCriterion("`insert_by` not between", value1, value2, "insertBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("`update_by` is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("`update_by` is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Long value) {
            addCriterion("`update_by` =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Long value) {
            addCriterion("`update_by` <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Long value) {
            addCriterion("`update_by` >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Long value) {
            addCriterion("`update_by` >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Long value) {
            addCriterion("`update_by` <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Long value) {
            addCriterion("`update_by` <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Long> values) {
            addCriterion("`update_by` in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Long> values) {
            addCriterion("`update_by` not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Long value1, Long value2) {
            addCriterion("`update_by` between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Long value1, Long value2) {
            addCriterion("`update_by` not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIsNull() {
            addCriterion("`insert_time` is null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIsNotNull() {
            addCriterion("`insert_time` is not null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeEqualTo(LocalDateTime value) {
            addCriterion("`insert_time` =", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotEqualTo(LocalDateTime value) {
            addCriterion("`insert_time` <>", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThan(LocalDateTime value) {
            addCriterion("`insert_time` >", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("`insert_time` >=", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThan(LocalDateTime value) {
            addCriterion("`insert_time` <", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("`insert_time` <=", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIn(List<LocalDateTime> values) {
            addCriterion("`insert_time` in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotIn(List<LocalDateTime> values) {
            addCriterion("`insert_time` not in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`insert_time` between", value1, value2, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`insert_time` not between", value1, value2, "insertTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("`update_time` is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("`update_time` is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(LocalDateTime value) {
            addCriterion("`update_time` =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("`update_time` <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(LocalDateTime value) {
            addCriterion("`update_time` >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("`update_time` >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(LocalDateTime value) {
            addCriterion("`update_time` <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("`update_time` <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<LocalDateTime> values) {
            addCriterion("`update_time` in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("`update_time` not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`update_time` between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`update_time` not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_login_channel
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_login_channel
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}