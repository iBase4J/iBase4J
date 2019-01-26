package org.ibase4j.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import top.ibase4j.core.base.BaseModel;

@TableName("sys_role")
@SuppressWarnings("serial")
public class SysRole extends BaseModel {
    private String roleName;
    private Long deptId;
    private String roleType;

    @TableField(exist = false)
    private String deptName;
    @TableField(exist = false)
    private String permission;

    /**
     * @return the value of sys_role.role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     *            the value for sys_role.role_name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * @return the value of sys_role.dept_id
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     *            the value for sys_role.dept_id
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * @return the value of sys_role.role_type
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * @param roleType
     *            the value for sys_role.role_type
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleName=").append(roleName);
        sb.append(", deptId=").append(deptId);
        sb.append(", roleType=").append(roleType);
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
        SysRole other = (SysRole) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getRoleName() == null ? other.getRoleName() == null
                : this.getRoleName().equals(other.getRoleName()))
                && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
                && (this.getRoleType() == null ? other.getRoleType() == null
                : this.getRoleType().equals(other.getRoleType()))
                && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null
                : this.getCreateBy().equals(other.getCreateBy()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null
                : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateBy() == null ? other.getUpdateBy() == null
                : this.getUpdateBy().equals(other.getUpdateBy()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null
                : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getId() == null ? 0 : getId().hashCode());
        result = prime * result + (getRoleName() == null ? 0 : getRoleName().hashCode());
        result = prime * result + (getDeptId() == null ? 0 : getDeptId().hashCode());
        result = prime * result + (getRoleType() == null ? 0 : getRoleType().hashCode());
        result = prime * result + (getEnable() == null ? 0 : getEnable().hashCode());
        result = prime * result + (getRemark() == null ? 0 : getRemark().hashCode());
        result = prime * result + (getCreateBy() == null ? 0 : getCreateBy().hashCode());
        result = prime * result + (getCreateTime() == null ? 0 : getCreateTime().hashCode());
        result = prime * result + (getUpdateBy() == null ? 0 : getUpdateBy().hashCode());
        result = prime * result + (getUpdateTime() == null ? 0 : getUpdateTime().hashCode());
        return result;
    }
}