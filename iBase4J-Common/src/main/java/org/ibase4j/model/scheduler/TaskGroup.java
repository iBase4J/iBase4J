package org.ibase4j.model.scheduler;

import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("task_group")
@SuppressWarnings("serial")
public class TaskGroup extends BaseModel {
    private String groupName;
    private String groupDesc;

    /**
     * @return the value of task_group.group_name
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the value for task_group.group_name
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * @return the value of task_group.group_desc
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * @param groupDesc the value for task_group.group_desc
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc == null ? null : groupDesc.trim();
    }

    /**
     * This method corresponds to the database table task_group
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupName=").append(groupName);
        sb.append(", groupDesc=").append(groupDesc);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method corresponds to the database table task_group
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
        TaskGroup other = (TaskGroup) that;
        return (this.getId_() == null ? other.getId_() == null : this.getId_().equals(other.getId_()))
            && (this.getGroupName() == null ? other.getGroupName() == null : this.getGroupName().equals(other.getGroupName()))
            && (this.getGroupDesc() == null ? other.getGroupDesc() == null : this.getGroupDesc().equals(other.getGroupDesc()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()));
    }

    /**
     * This method corresponds to the database table task_group
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId_() == null) ? 0 : getId_().hashCode());
        result = prime * result + ((getGroupName() == null) ? 0 : getGroupName().hashCode());
        result = prime * result + ((getGroupDesc() == null) ? 0 : getGroupDesc().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        return result;
    }
}