package org.ibase4j.model.scheduler;

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("task_scheduler")
@SuppressWarnings("serial")
public class TaskScheduler extends BaseModel {
    private Long groupId;
    private String taskName;
    private String taskType;
    private String taskDesc;
    private String taskCron;
    private String contactEmail;

    /**
     * @return the value of task_scheduler.group_id
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the value for task_scheduler.group_id
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the value of task_scheduler.task_name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName the value for task_scheduler.task_name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    /**
     * @return the value of task_scheduler.task_type
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * @param taskType the value for task_scheduler.task_type
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    /**
     * @return the value of task_scheduler.task_desc
     */
    public String getTaskDesc() {
        return taskDesc;
    }

    /**
     * @param taskDesc the value for task_scheduler.task_desc
     */
    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc == null ? null : taskDesc.trim();
    }

    /**
     * @return the value of task_scheduler.task_cron
     */
    public String getTaskCron() {
        return taskCron;
    }

    /**
     * @param taskCron the value for task_scheduler.task_cron
     */
    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron == null ? null : taskCron.trim();
    }

    /**
     * @return the value of task_scheduler.contact_email
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * @param contactEmail the value for task_scheduler.contact_email
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    /**
     * This method corresponds to the database table task_scheduler
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupId=").append(groupId);
        sb.append(", taskName=").append(taskName);
        sb.append(", taskType=").append(taskType);
        sb.append(", taskDesc=").append(taskDesc);
        sb.append(", taskCron=").append(taskCron);
        sb.append(", contactEmail=").append(contactEmail);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method corresponds to the database table task_scheduler
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TaskScheduler other = (TaskScheduler) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
            && (this.getTaskType() == null ? other.getTaskType() == null : this.getTaskType().equals(other.getTaskType()))
            && (this.getTaskDesc() == null ? other.getTaskDesc() == null : this.getTaskDesc().equals(other.getTaskDesc()))
            && (this.getTaskCron() == null ? other.getTaskCron() == null : this.getTaskCron().equals(other.getTaskCron()))
            && (this.getContactEmail() == null ? other.getContactEmail() == null : this.getContactEmail().equals(other.getContactEmail()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method corresponds to the database table task_scheduler
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
        result = prime * result + ((getTaskType() == null) ? 0 : getTaskType().hashCode());
        result = prime * result + ((getTaskDesc() == null) ? 0 : getTaskDesc().hashCode());
        result = prime * result + ((getTaskCron() == null) ? 0 : getTaskCron().hashCode());
        result = prime * result + ((getContactEmail() == null) ? 0 : getContactEmail().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}