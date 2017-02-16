package org.ibase4j.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("task_fire_log")
@SuppressWarnings("serial")
public class TaskFireLog implements Serializable {
    @TableId("id_")
    private Long id;
    private String groupName;
    private String taskName;
    private Date startTime;
    private Date endTime;
    @TableField("status_")
    private String status;
    private String serverHost;
    private String serverDuid;
    private String fireInfo;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the value of task_fire_log.group_name
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the value for task_fire_log.group_name
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * @return the value of task_fire_log.task_name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName the value for task_fire_log.task_name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    /**
     * @return the value of task_fire_log.start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the value for task_fire_log.start_time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the value of task_fire_log.end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the value for task_fire_log.end_time
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the value of task_fire_log.status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the value for task_fire_log.status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return the value of task_fire_log.server_host
     */
    public String getServerHost() {
        return serverHost;
    }

    /**
     * @param serverHost the value for task_fire_log.server_host
     */
    public void setServerHost(String serverHost) {
        this.serverHost = serverHost == null ? null : serverHost.trim();
    }

    /**
     * @return the value of task_fire_log.server_duid
     */
    public String getServerDuid() {
        return serverDuid;
    }

    /**
     * @param serverDuid the value for task_fire_log.server_duid
     */
    public void setServerDuid(String serverDuid) {
        this.serverDuid = serverDuid == null ? null : serverDuid.trim();
    }

    /**
     * @return the value of task_fire_log.fire_info
     */
    public String getFireInfo() {
        return fireInfo;
    }

    /**
     * @param fireInfo the value for task_fire_log.fire_info
     */
    public void setFireInfo(String fireInfo) {
        this.fireInfo = fireInfo == null ? null : fireInfo.trim();
    }

    /**
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupName=").append(groupName);
        sb.append(", taskName=").append(taskName);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", status=").append(status);
        sb.append(", serverHost=").append(serverHost);
        sb.append(", serverDuid=").append(serverDuid);
        sb.append(", fireInfo=").append(fireInfo);
        sb.append("]");
        return sb.toString();
    }

    /**
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
        TaskFireLog other = (TaskFireLog)that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGroupName() == null ? other.getGroupName() == null
                : this.getGroupName().equals(other.getGroupName()))
            && (this.getTaskName() == null ? other.getTaskName() == null
                : this.getTaskName().equals(other.getTaskName()))
            && (this.getStartTime() == null ? other.getStartTime() == null
                : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getServerHost() == null ? other.getServerHost() == null
                : this.getServerHost().equals(other.getServerHost()))
            && (this.getServerDuid() == null ? other.getServerDuid() == null
                : this.getServerDuid().equals(other.getServerDuid()))
            && (this.getFireInfo() == null ? other.getFireInfo() == null
                : this.getFireInfo().equals(other.getFireInfo()));
    }

    /**
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGroupName() == null) ? 0 : getGroupName().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getServerHost() == null) ? 0 : getServerHost().hashCode());
        result = prime * result + ((getServerDuid() == null) ? 0 : getServerDuid().hashCode());
        result = prime * result + ((getFireInfo() == null) ? 0 : getFireInfo().hashCode());
        return result;
    }
}
