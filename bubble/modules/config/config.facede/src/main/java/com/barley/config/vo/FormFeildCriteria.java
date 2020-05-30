package com.barley.config.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FormFeildCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_form_feild
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_form_feild
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_form_feild
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_form_feild
     *
     * @mbg.generated
     */
    public FormFeildCriteria() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_form_feild
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_form_feild
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_form_feild
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_form_feild
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_form_feild
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_form_feild
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_form_feild
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
     * This method corresponds to the database table t_form_feild
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
     * This method corresponds to the database table t_form_feild
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_form_feild
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
     * This class corresponds to the database table t_form_feild
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

        public Criteria andListIdEqualTo(Short value) {
            addCriterion("`list_id` =", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdNotEqualTo(Short value) {
            addCriterion("`list_id` <>", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdGreaterThan(Short value) {
            addCriterion("`list_id` >", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdGreaterThanOrEqualTo(Short value) {
            addCriterion("`list_id` >=", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdLessThan(Short value) {
            addCriterion("`list_id` <", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdLessThanOrEqualTo(Short value) {
            addCriterion("`list_id` <=", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdIn(List<Short> values) {
            addCriterion("`list_id` in", values, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdNotIn(List<Short> values) {
            addCriterion("`list_id` not in", values, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdBetween(Short value1, Short value2) {
            addCriterion("`list_id` between", value1, value2, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdNotBetween(Short value1, Short value2) {
            addCriterion("`list_id` not between", value1, value2, "listId");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNull() {
            addCriterion("`form_id` is null");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNotNull() {
            addCriterion("`form_id` is not null");
            return (Criteria) this;
        }

        public Criteria andFormIdEqualTo(String value) {
            addCriterion("`form_id` =", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotEqualTo(String value) {
            addCriterion("`form_id` <>", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThan(String value) {
            addCriterion("`form_id` >", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThanOrEqualTo(String value) {
            addCriterion("`form_id` >=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThan(String value) {
            addCriterion("`form_id` <", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThanOrEqualTo(String value) {
            addCriterion("`form_id` <=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLike(String value) {
            addCriterion("`form_id` like", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotLike(String value) {
            addCriterion("`form_id` not like", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdIn(List<String> values) {
            addCriterion("`form_id` in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotIn(List<String> values) {
            addCriterion("`form_id` not in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdBetween(String value1, String value2) {
            addCriterion("`form_id` between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotBetween(String value1, String value2) {
            addCriterion("`form_id` not between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andFeildNameIsNull() {
            addCriterion("`feild_name` is null");
            return (Criteria) this;
        }

        public Criteria andFeildNameIsNotNull() {
            addCriterion("`feild_name` is not null");
            return (Criteria) this;
        }

        public Criteria andFeildNameEqualTo(String value) {
            addCriterion("`feild_name` =", value, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildNameNotEqualTo(String value) {
            addCriterion("`feild_name` <>", value, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildNameGreaterThan(String value) {
            addCriterion("`feild_name` >", value, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildNameGreaterThanOrEqualTo(String value) {
            addCriterion("`feild_name` >=", value, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildNameLessThan(String value) {
            addCriterion("`feild_name` <", value, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildNameLessThanOrEqualTo(String value) {
            addCriterion("`feild_name` <=", value, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildNameLike(String value) {
            addCriterion("`feild_name` like", value, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildNameNotLike(String value) {
            addCriterion("`feild_name` not like", value, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildNameIn(List<String> values) {
            addCriterion("`feild_name` in", values, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildNameNotIn(List<String> values) {
            addCriterion("`feild_name` not in", values, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildNameBetween(String value1, String value2) {
            addCriterion("`feild_name` between", value1, value2, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildNameNotBetween(String value1, String value2) {
            addCriterion("`feild_name` not between", value1, value2, "feildName");
            return (Criteria) this;
        }

        public Criteria andFeildTypeIsNull() {
            addCriterion("`feild_type` is null");
            return (Criteria) this;
        }

        public Criteria andFeildTypeIsNotNull() {
            addCriterion("`feild_type` is not null");
            return (Criteria) this;
        }

        public Criteria andFeildTypeEqualTo(Short value) {
            addCriterion("`feild_type` =", value, "feildType");
            return (Criteria) this;
        }

        public Criteria andFeildTypeNotEqualTo(Short value) {
            addCriterion("`feild_type` <>", value, "feildType");
            return (Criteria) this;
        }

        public Criteria andFeildTypeGreaterThan(Short value) {
            addCriterion("`feild_type` >", value, "feildType");
            return (Criteria) this;
        }

        public Criteria andFeildTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("`feild_type` >=", value, "feildType");
            return (Criteria) this;
        }

        public Criteria andFeildTypeLessThan(Short value) {
            addCriterion("`feild_type` <", value, "feildType");
            return (Criteria) this;
        }

        public Criteria andFeildTypeLessThanOrEqualTo(Short value) {
            addCriterion("`feild_type` <=", value, "feildType");
            return (Criteria) this;
        }

        public Criteria andFeildTypeIn(List<Short> values) {
            addCriterion("`feild_type` in", values, "feildType");
            return (Criteria) this;
        }

        public Criteria andFeildTypeNotIn(List<Short> values) {
            addCriterion("`feild_type` not in", values, "feildType");
            return (Criteria) this;
        }

        public Criteria andFeildTypeBetween(Short value1, Short value2) {
            addCriterion("`feild_type` between", value1, value2, "feildType");
            return (Criteria) this;
        }

        public Criteria andFeildTypeNotBetween(Short value1, Short value2) {
            addCriterion("`feild_type` not between", value1, value2, "feildType");
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
     * This class corresponds to the database table t_form_feild
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
     * This class corresponds to the database table t_form_feild
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