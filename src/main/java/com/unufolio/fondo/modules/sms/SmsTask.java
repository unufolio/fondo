package com.unufolio.fondo.modules.sms;

import java.time.LocalDateTime;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/05/03
 */
public class SmsTask {

    private String id;
    private String template;
    private String topic;
    private String content;
    private Boolean isDraft;
    private Integer status;
    private String receiver;
    private Boolean isDeleted;
    private LocalDateTime sendTime;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String createBy;
    private String updateBy;

    public SmsTask() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "SmsTask{" +
                "id='" + id + '\'' +
                ", template='" + template + '\'' +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
