package com.barley.system.modal;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.barley.bo.AutomaticEntityImpl;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_message
 */
public class SysMessage extends AutomaticEntityImpl implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_message.list_id
     *
     * @mbg.generated
     */
    private Integer listId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_message.message_body
     *
     * @mbg.generated
     */
    private String messageBody;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_message.recipient
     *
     * @mbg.generated
     */
    private Long recipient;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_message.sender
     *
     * @mbg.generated
     */
    private Long sender;

    /**
     * Database Column Remarks:
     *   消息是否已经消费
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_message.consumption
     *
     * @mbg.generated
     */
    private String consumption;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_message.expiry_date
     *
     * @mbg.generated
     */
    private LocalDateTime expiryDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_message
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_message.list_id
     *
     * @return the value of t_message.list_id
     *
     * @mbg.generated
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_message.list_id
     *
     * @param listId the value for t_message.list_id
     *
     * @mbg.generated
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_message.message_body
     *
     * @return the value of t_message.message_body
     *
     * @mbg.generated
     */
    public String getMessageBody() {
        return messageBody;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_message.message_body
     *
     * @param messageBody the value for t_message.message_body
     *
     * @mbg.generated
     */
    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody == null ? null : messageBody.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_message.recipient
     *
     * @return the value of t_message.recipient
     *
     * @mbg.generated
     */
    public Long getRecipient() {
        return recipient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_message.recipient
     *
     * @param recipient the value for t_message.recipient
     *
     * @mbg.generated
     */
    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_message.sender
     *
     * @return the value of t_message.sender
     *
     * @mbg.generated
     */
    public Long getSender() {
        return sender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_message.sender
     *
     * @param sender the value for t_message.sender
     *
     * @mbg.generated
     */
    public void setSender(Long sender) {
        this.sender = sender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_message.consumption
     *
     * @return the value of t_message.consumption
     *
     * @mbg.generated
     */
    public String getConsumption() {
        return consumption;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_message.consumption
     *
     * @param consumption the value for t_message.consumption
     *
     * @mbg.generated
     */
    public void setConsumption(String consumption) {
        this.consumption = consumption == null ? null : consumption.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_message.expiry_date
     *
     * @return the value of t_message.expiry_date
     *
     * @mbg.generated
     */
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_message.expiry_date
     *
     * @param expiryDate the value for t_message.expiry_date
     *
     * @mbg.generated
     */
    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}