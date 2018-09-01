package org.ibase4j.model.sys;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import top.ibase4j.core.base.BaseModel;

@TableName("sys_role_menu")
@SuppressWarnings("serial")
public class SysRoleMenu extends BaseModel {
    private Long roleId;
    private Long menuId;
    @TableField("permission_")
    private String permission;

    public SysRoleMenu() {
    }

    public SysRoleMenu(Long roleId, Long menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    /**
     * @return the value of sys_role_menu.role_id
     */
    public Long getRoleId() {
        return roleId;
    }

	/**
     * @param roleId
     *            the value for sys_role_menu.role_id
     * @return 
     */
    public SysRoleMenu setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    /**
     * @return the value of sys_role_menu.menu_id
     */
    public Long getMenuId() {
        return menuId;
    }

	/**
     * @param menuId
     *            the value for sys_role_menu.menu_id
     * @return 
     */
    public SysRoleMenu setMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    /**
     * @return the value of sys_role_menu.permission_
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission
     *            the value for sys_role_menu.permission_
     */
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    /**
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", menuId=").append(menuId);
        sb.append(", permission=").append(permission);
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
        SysRoleMenu other = (SysRoleMenu) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
                && (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
                && (this.getPermission() == null ? other.getPermission() == null
                : this.getPermission().equals(other.getPermission()))
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
        result = prime * result + (getRoleId() == null ? 0 : getRoleId().hashCode());
        result = prime * result + (getMenuId() == null ? 0 : getMenuId().hashCode());
        result = prime * result + (getPermission() == null ? 0 : getPermission().hashCode());
        result = prime * result + (getEnable() == null ? 0 : getEnable().hashCode());
        result = prime * result + (getRemark() == null ? 0 : getRemark().hashCode());
        result = prime * result + (getCreateBy() == null ? 0 : getCreateBy().hashCode());
        result = prime * result + (getCreateTime() == null ? 0 : getCreateTime().hashCode());
        result = prime * result + (getUpdateBy() == null ? 0 : getUpdateBy().hashCode());
        result = prime * result + (getUpdateTime() == null ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
